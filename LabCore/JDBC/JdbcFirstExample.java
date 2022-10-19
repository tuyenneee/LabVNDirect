import javax.sql.RowSet;
import javax.sql.rowset.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;

public class JdbcFirstExample {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        Statement statement = null;
        try {
            File file = new File("./sampledb");
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            connection = DriverManager.getConnection("jdbc:derby:" + file.getAbsolutePath() + ";create=true");
            statement = connection.createStatement();

            System.out.println("db path " + file.getAbsolutePath());
            System.out.println("Create database successful!");
            String sql = "create table student (" +
                    "id bigint primary key" +
                    " generated always as identity (start with 1, increment by 1)," +
                    "name varchar(1000)," +
                    "age integer default 20)";
            System.out.println(statement.execute(sql));
        } finally {
            statement.close();
            connection.close();
        }
    }
}

class InsertDataExample {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        Statement statement = null;
        try {
            File file = new File("./sampledb");
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            connection = DriverManager.getConnection("jdbc:derby:" + file.getAbsolutePath() + ";create=true");
            statement = connection.createStatement();

            statement.executeUpdate("insert into student (name, age) values ('Tran Van B', 20)");
            statement.executeUpdate("insert into student (name, age) values ('Tran Thi B', 12)");
            statement.executeUpdate("insert into student (name, age) values ('Nguyen Van C', 56)");
            System.out.println(123);
        } finally {
            connection.close();
            statement.close();
        }
    }
}

class SelectDataExample {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        Statement statement = null;
        File file = new File("./sampledb");
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        connection = DriverManager.getConnection("jdbc:derby:" + file.getAbsolutePath() + ";create=true");
        statement = connection.createStatement();

        ResultSet rs = statement.executeQuery("select * from student");
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            int age = rs.getInt(3);

            System.out.println(id + "\t" + name + "\t" + age);
        }
        rs.close();
        connection.close();
    }
}

class UpdateDataExample {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        File file = new File("./sampledb");
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        connection = DriverManager.getConnection("jdbc:derby:" + file.getAbsolutePath() + ";create=true");
        PreparedStatement statement = connection.prepareStatement("update student set name = ? where id = ?");
        statement.setString(1, "Le Thi Z");
        statement.setInt(2, 2);
        statement.executeUpdate();

        connection.close();
    }
}

class TransactionExample {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        Statement statement = null;
        File file = new File("./sampledb");
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        connection = DriverManager.getConnection("jdbc:derby:" + file.getAbsolutePath() + ";create=true");
        statement = connection.createStatement();

        connection.setAutoCommit(false);
        for (int i = 0; i < 10; i++) {
            String name = "Tran Van " + i;
            int age = 10 + i;
            statement.executeUpdate("insert into student(name, age) values ('" + name + "', " + age + ")");
        }
        connection.rollback();

        connection.setAutoCommit(true);
        ResultSet rs = statement.executeQuery("select count(*) from student");
        if (rs.next()) System.out.println("Total: " + rs.getInt(1));
        connection.close();
    }
}

class BatchProcessingExample {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        Statement statement = null;
        File file = new File("./sampledb");
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        connection = DriverManager.getConnection("jdbc:derby:" + file.getAbsolutePath() + ";create=true");
        statement = connection.createStatement();

        for (int i = 0; i < 20; i++) {
            String name = "Nguyen Van " + i;
            int age = 10 + i;
            String sql = "insert into student(name, age) values ('" + name + "', " + age + ")";
            statement.addBatch(sql);
        }
        statement.executeBatch();

        ResultSet rs = statement.executeQuery("select count(*) from student");
        if (rs.next()) System.out.println("Total = " + rs.getInt(1));
        connection.close();
    }
}

class JdbcMetadataExample {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        Statement statement = null;
        try {
            File file = new File("./sampledb");
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            connection = DriverManager.getConnection("jdbc:derby:" + file.getAbsolutePath() + ";create=true");
            statement = connection.createStatement();

            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println("db version " + metaData.getDatabaseMajorVersion());
            System.out.println("driver name " + metaData.getDriverName());
        } finally {
            connection.close();
        }
    }
}

class jdbcRowSetExample {
    public static void main(String[] args) {
        try (JdbcRowSet jdbcRs = RowSetProvider.newFactory().createJdbcRowSet();) {
            File file = new File("./sampledb");
            jdbcRs.setUrl("jdbc:derby:" + file);
            jdbcRs.setCommand("select * from student");
            jdbcRs.execute();

            jdbcRs.first();
            jdbcRs.updateString("name", "Nguyen Van Tuyen");
            jdbcRs.commit();

            System.out.println(jdbcRs.getInt("id") + "\t" + jdbcRs.getString("name") + "\t" + jdbcRs.getInt("age"));

            jdbcRs.absolute(8);
            System.out.println(jdbcRs.getString(2) + " : " + jdbcRs.getInt(3));

//            while (jdbcRs.next()){
//                System.out.println(jdbcRs.getInt(1)+"\t"+jdbcRs.getString(2)+"\t"+jdbcRs.getInt(3));
//            }
            //jdbcRs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

class DataAccessLogic {
    private final static Logger logger = Logger.getLogger(DataAccessLogic.class.getName());
    Connection connection = null;
    CachedRowSet rowSet;

    {
        try {
            rowSet = RowSetProvider.newFactory().createCachedRowSet();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public DataAccessLogic() throws ClassNotFoundException, SQLException {
        File file = new File("./sampledb");
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        this.connection = DriverManager.getConnection("jdbc:derby:" + file.getAbsolutePath() + ";create=true");
        rowSet = RowSetProvider.newFactory().createCachedRowSet();
        rowSet.setCommand("select * from student");
        rowSet.execute(connection);
    }

    public void disconnect() {
        try {
            if (connection != null) connection.close();
        } catch (SQLException e) {
            logger.log(Level.WARNING, e.toString());
        }
    }

    int pageSize = 10;

    List<String> loadNames(int page) throws SQLException {
        List<String> names = new ArrayList<>();
        rowSet.setPageSize(pageSize);
        int start = (page - 1) * pageSize + 1;
        if (!rowSet.absolute(start)) return names;

        rowSet.previous();
        while (rowSet.next()) {
            names.add(rowSet.getString("name"));
            if (names.size() >= pageSize) break;
        }
        return names;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        DataAccessLogic dada = new DataAccessLogic();
//        List<String> names = dada.loadNames(1);
//        names.forEach(System.out::println);
//        System.out.println("Total page = "+dada.numberOfPage());
        IntStream.range(1, dada.numberOfPage() + 1).forEach(page -> {
            System.out.println("============================= " + page);
            List<String> names = null;
            try {
                names = dada.loadNames(page);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            System.out.println(names);
        });
    }

    public int numberOfPage() throws SQLException {
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select count(*) from student");
            if (!rs.next()) return 0;
            int total = rs.getInt(1);
            int totalPage = total / pageSize;
            if (total % pageSize != 0) totalPage++;
            return totalPage;
        } finally {
            statement.close();
        }
    }
}

class WebRowSetExample {
    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
        Connection connection = null;
        Statement statement = null;
        try {
            File file = new File("./sampledb");
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            connection = DriverManager.getConnection("jdbc:derby:" + file.getAbsolutePath() + ";create=true");
            statement = connection.createStatement();

            ResultSet rs = statement.executeQuery("select * from student");
            File file1 = new File("output.xml");
            FileWriter writer = new FileWriter(file1);
            WebRowSet wrs = RowSetProvider.newFactory().createWebRowSet();
            wrs.writeXml(rs, writer);

            Desktop.getDesktop().open(file1);
        } finally {

        }
    }
}

class DataFilter implements Predicate {

    @Override
    public boolean evaluate(RowSet rs) {
        CachedRowSet crs = (CachedRowSet) rs;
        try {
            return crs.getString("name").indexOf("Nguyen") > -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean evaluate(Object value, int column) throws SQLException {
        return false;
    }

    @Override
    public boolean evaluate(Object value, String columnName) throws SQLException {
        return false;
    }
}

class DataFilterExample {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Connection connection = null;

        File file = new File("./sampledb");
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        connection = DriverManager.getConnection("jdbc:derby:" + file.getAbsolutePath() + ";create=true");

        FilteredRowSet frs = RowSetProvider.newFactory().createFilteredRowSet();
        frs.setUrl("jdbc:derby:" + file);

        frs.setCommand("select * from student");
        frs.setFilter(new DataFilter());
        frs.execute();

        System.out.println("ID\tName\tAge");
        while (frs.next()) {
            System.out.println(frs.getInt(1) + "\t" + frs.getString(2) + "\t" + frs.getInt(3));
        }
    }
}

class DbFunction {
    public static void getAge(String name, int[] ages) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        Statement statement = null;
        try {
            File file = new File("./sampledb");
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            connection = DriverManager.getConnection("jdbc:derby:" + file.getAbsolutePath() + ";create=true");
            statement = connection.createStatement();

            ResultSet rs = statement.executeQuery("select max(age) from student where name like '%" + name + "%'");
            ages[0] = rs.next() ? rs.getInt(1) : -1;
        } finally {
            connection.close();
        }
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        int[] ages = new int[1];
        getAge("Nguyen", ages);
        System.out.println(ages[0]);
    }
}

class CreateStoredProcedureExample {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        Statement statement = null;

        File file = new File("./sampledb");
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        connection = DriverManager.getConnection("jdbc:derby:" + file.getAbsolutePath() + ";create=true");
        statement = connection.createStatement();

        statement.executeUpdate("create procedure GETAGE(STREAM_NAME VARCHAR(255), " +
                "OUT AGE INT) PARAMETER STYLE JAVA READS " +
                "SQL DATA LANGUAGE JAVA EXTERNAL NAME " +
                "'jdbc.DBFunction.getAge'");
        System.out.println("Done");
        connection.close();
    }
}
