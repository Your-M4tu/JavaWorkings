package comceit.expensetrackerapi.repositories;

import comceit.expensetrackerapi.domains.User;
import comceit.expensetrackerapi.exceptions.EAuthExceptions;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
public class UserRepositoryImpl implements UserRepository{

    private static final String SQL_CREATE = "INSERT INTO et_users (user_id,first_name,last_name,email,password) VALUES (NEXTVAL('et_users_seq'),?,?,?,?)";
    private static final String SQL_FIND_BY_ID = "SELECT user_id, first_name,last_name,email,password FROM et_users WHERE user_id = ?";
    private static final String SQL_COUNT_BY_EMAIL = "SELECT COUNT(*) FROM et_users WHERE email = ?";
    private static final String SQL_FIND_BY_EMAIL = "SELECT user_id, first_name, last_name, email, password FROM et_users WHERE email = ?";

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public Integer create(String firstName, String lastName, String email, String password) throws EAuthExceptions {
        try{
            KeyHolder keyHolder = new GeneratedKeyHolder();
            String hashedPassword = BCrypt.hashpw(password,BCrypt.gensalt(10));
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1,firstName);
                ps.setString(2,lastName);
                ps.setString(3,email);
                ps.setString(4,hashedPassword);
                return ps;
            },keyHolder);
            return (Integer) keyHolder.getKeys().get("user_id");
        } catch (Exception e){
            throw new EAuthExceptions("Invalid details");
        }
    }

    @Override
    public User findByEmailAndPassword(String email, String password) throws EAuthExceptions {
        try {
            User user = jdbcTemplate.queryForObject(SQL_FIND_BY_EMAIL, new Object[]{email},userRowMapper);
            return user;
        }
        catch (EmptyResultDataAccessException e){
            throw new EAuthExceptions("Invalid Email / Password");
        }
    }

    @Override
    public Integer getCountByEmail(String email) {
        return jdbcTemplate.queryForObject(SQL_COUNT_BY_EMAIL, new Object[] {email}, Integer.class);
    }

    @Override
    public User findById(Integer userId) {
        return jdbcTemplate.queryForObject(SQL_FIND_BY_ID,new Object[]{userId},userRowMapper);
    }
    private RowMapper<User> userRowMapper = ((rs, rowNum) -> {
        return new User (
                rs.getInt("USER_ID"),
                rs.getString("FIRST_NAME"),
                rs.getString("LAST_NAME"),
                rs.getString("EMAIL"),
                rs.getString("PASSWORD")
        );
    });
}
