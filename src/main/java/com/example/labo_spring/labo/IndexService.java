
package com.example.labo_spring.labo;

        import com.example.labo_spring.model.model.IndexRepository;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.dao.DataAccessException;
        import org.springframework.stereotype.Service;

        import java.util.Collections;
        import java.util.List;

@Service

public class IndexService {
    @Autowired
    private IndexRepository repository;

    public List<Index> laboEachClass(String laboClass){
        try{
            return repository.select(laboClass);
        }catch (DataAccessException e){
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}
