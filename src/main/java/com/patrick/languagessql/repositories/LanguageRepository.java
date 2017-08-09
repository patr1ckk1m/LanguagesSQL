package com.patrick.languagessql.repositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.patrick.languagessql.models.*;

@Repository
public interface LanguageRepository extends CrudRepository<Language, Long>{

}
