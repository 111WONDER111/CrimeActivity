package database.CrimeDbSchema;

/**
 * 定义数据库schema（模式）
 */
public class CrimeDbSchema {
    // CrimeTable是描述数据表的内部类
    public static final class CrimeTable {
        public static final String NAME = "crimes"; // 定义数据库表名（CrimeTable.NAME）

        // 定义数据表字段，例如CrimeTable.Cols.TITLE就是指crime记录的title字段
        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String TITLE = "title";
            public static final String DATE = "date";
            public static final String SOLVED = "solved";

            public static final String SUSPECT = "suspect"; // p252

        }
    }
}
