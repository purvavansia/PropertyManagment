package com.example.purva.propertymanagment.data.model;

import android.provider.BaseColumns;

/**
 * Created by purva on 4/29/18.
 */

public class TaskContract {

    public static final String DB_NAME = "task_db";
    public static final int DB_VERSION = 1;

    public class TaskEntry implements BaseColumns {
        public static final String TABLE = "tasks";

        public static final String COL_TASK_TITLE = "title";
    }
}
