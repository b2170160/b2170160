

package com.example.labo_spring.model.model;

        import com.example.labo_spring.labo.Index;
        import com.example.labo_spring.labo.Labo;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.jdbc.core.JdbcTemplate;
        import org.springframework.stereotype.Repository;

        import java.util.List;

        import static org.springframework.jdbc.core.BeanPropertyRowMapper.newInstance;

@Repository
public class LaboRepository {
    @Autowired
    private JdbcTemplate jdbc;

    public Labo findById(String laboId){
        var sql = "Select LaboId,LaboName from laboAbout where laboId = ? ;";
        return jdbc.queryForObject(sql,newInstance(Labo.class),laboId);
    }
    public List<Labo> selectStudent(String laboId){
        var sql = "Select LaboId,STUDENTID,name from LABOMEMBER where laboId = '" + laboId +"';";
        return jdbc.query(sql,newInstance(Labo.class));
    }

    public Labo findraboId(String studentId){
        var sql = "Select LaboId from LABOMEMBER where studentId = ? ;";
        return jdbc.queryForObject(sql,newInstance(Labo.class),studentId);
    }

    public Labo findname(String studentId){
        var sql = "Select name from LABOMEMBER where studentId = ? ;";
        return jdbc.queryForObject(sql,newInstance(Labo.class),studentId);
    }
}
