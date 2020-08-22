package SpringJDBC;

import com.alibaba.druid.support.json.JSONUtils;
import connectionPool.JdbcUtilsWithDruid;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

public class JdbcTemplateUsage {
    public static void main(String[] args) {
        JdbcTemplate template = new JdbcTemplate(JdbcUtilsWithDruid.getDataSource());

        List<User> users = template.query("select * from salarytbl", new BeanPropertyRowMapper<>(User.class));

        users.forEach(u-> System.out.println(u));
    }
}
