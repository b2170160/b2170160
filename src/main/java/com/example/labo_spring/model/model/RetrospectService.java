
package com.example.labo_spring.model.model;

        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.dao.DataAccessException;
        import org.springframework.stereotype.Service;

        import java.util.List;

        import static java.util.Collections.emptyList;

@Service
public class RetrospectService {

    @Autowired
    private  RetrospectRepository repository;

    public String register(String text,String page_name){
        var retrospect=new Retrospect(text,page_name);

        try{
            if(!(retrospect.getText().isEmpty())){

                var n = repository.insert(retrospect);
                var message=n+"件を追加";
                return message;
            }
            var y="コメントを入力してください";
            return y;
        }catch(DataAccessException e){
            var x="接続失敗";
            return x;
        }
    }
    public List<Retrospect> findAll(String page_name){

        var retrospectuser = new Retrospectuser(page_name);

        try{
            return repository.select(retrospectuser);
        }catch(DataAccessException e){
            e.printStackTrace();
        }
        return emptyList();
    }
}
