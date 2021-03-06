package data;

import org.apache.log4j.Logger;

/**
 * Created by Max Poznyak
 * on 11/21/18  at 20:05
 */

public class DataStateListener {

    private static volatile DataStateListener listener;

    private Boolean actualDataState = true;
    private Boolean initialDataLoaded = false;
    private static final Logger logger = Logger.getLogger(DataStateListener.class);


    private DataStateListener() {
    }

    public Boolean getDataState() {
        return actualDataState;
    }

    public Boolean isInitialDataLoaded() {
        return initialDataLoaded;
    }

    public void setInitialDataLoaded(Boolean loaded) {
        initialDataLoaded = loaded;
    }

    public void resetDataState() {
        actualDataState = true;
    }

    public Boolean dataIsNotActual() {
        logger.info("[START] dataIsNotActual()");
        actualDataState = false;
        logger.info("[END] dataIsNotActual()");
        return actualDataState;
    }

    public static DataStateListener getInstance() {
        DataStateListener localInstance = listener;
        if (localInstance == null) {
            synchronized (DataStateListener.class) {
                localInstance = listener;
                if (localInstance == listener) {
                    localInstance = listener = new DataStateListener();
                }
            }
        }
        return localInstance;
    }
}
