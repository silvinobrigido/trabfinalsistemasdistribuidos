package com.furb.mancalajogo.repository;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.furb.mancalajogo.model.MancalaJogo;

public interface MancalaRepositorioJogo extends MongoRepository<MancalaJogo, String>{

}
