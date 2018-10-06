package by.makhavenka.task.poolconnection;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


import java.sql.*;

import java.util.ArrayDeque;
import java.util.Enumeration;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Connection pool for operations with freeConnections.
 */
public class Pool {

    private static final Logger LOGGER=LogManager.getLogger(Pool.class);

    private BlockingQueue<ConnectionProxy> availableConnections;
    private ArrayDeque<ConnectionProxy> usedConnections;

    private int poolSize;
    private static Pool instance;
    private static AtomicBoolean instanceCreated=new AtomicBoolean(false);
    private static ReentrantLock lock= new ReentrantLock();

    private Pool(){
       if(instance!=null){ throw  new RuntimeException("can not clone the object");}
       poolSize=DbConfig.dbPoolSize;
       createPool();
    }

    /**
     * Returns instance of the connection pool.
     *
     * @return Pool
     */
    public static Pool getInstance(){
            if(!instanceCreated.get()) {
                lock.lock();
                try {
                    if (instance == null) {
                        instance = new Pool();
                        instanceCreated.set(true);
                    }
                } finally {
                    lock.unlock();
                }
            }
        return instance;
    }

    @Override
    public Object clone() throws CloneNotSupportedException{
        if(instance!=null){
            throw new CloneNotSupportedException();
        }
        return super.clone();
    }

    private void createPool(){
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            availableConnections=new ArrayBlockingQueue<>(poolSize);
            usedConnections=new ArrayDeque<>(poolSize);
            for (int i = 0; i <poolSize ; i++) {
               ConnectionProxy connectionProxy= new ConnectionProxy(ConnectorDB.getConnection());
               availableConnections.add(connectionProxy);
            }
        }catch (SQLException e){
            LOGGER.fatal("error creating the pool",e);
            throw new RuntimeException("error creating the pool", e);
        }
    }

    /**
     * close all connection
     */
    public void clearConnectionQueues(){
            closeConnectionQueue();
    }


    private void closeConnectionQueue(){
        try {
            ConnectionProxy proxyConnection;
            for(int i=0;i<poolSize;i++){
                proxyConnection = availableConnections.take();
                proxyConnection.realClose();
            }
        } catch (SQLException e){
            LOGGER.error(e.getMessage(),e);
        } catch (InterruptedException e){
            LOGGER.error("close all connection error",e);
        }finally {
            try{
                Enumeration<java.sql.Driver> enumeration = DriverManager.getDrivers();
                while (enumeration.hasMoreElements()){
                    Driver driver =enumeration.nextElement();
                    DriverManager.deregisterDriver(driver);
                }
            }catch (SQLException e){
                LOGGER.warn("deregisterDriver error",e);
            }
        }
    }

    /**
     * take free connection from poll
     * @return ConnectionProxy
     */
    public ConnectionProxy takeConnection(){
       ConnectionProxy connection=null;
       try{
           connection=availableConnections.take();
           usedConnections.add(connection);
       }catch (InterruptedException e){
           LOGGER.error("error take connection",e);
       }
       return connection;
    }

    /**
     * release connectin to pool
     * @param connection
     */
    public void releaseConnection(ConnectionProxy connection){
        try {
            if(connection.isClosed()){ throw  new SQLException("Close closen connection");}
            if(connection.isReadOnly()){connection.setReadOnly(false);}
            connection.setAutoCommit(true);
            if(!usedConnections.remove(connection)){ throw new SQLException("error deleting connection");}
            if(!availableConnections.offer(connection)){
                throw new SQLException("error put back connection to the pool");}
        }catch (SQLException e){
            LOGGER.warn("Connection isn't in pool");
        }
    }
}
