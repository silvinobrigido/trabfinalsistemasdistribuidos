package com.furb.mancalajogo.repository;

import com.furb.mancalajogo.model.MancalaJogo;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@DataMongoTest
@RunWith(SpringRunner.class)
@DirtiesContext
public class MancalaRepositorioTests {

    @Autowired
    MancalaRepositorioJogo gameRepository;

    @Test
    public void testRepository() throws  Exception {
        this.gameRepository.deleteAll();

        MancalaJogo saved= this.gameRepository.save(new MancalaJogo(6));

        Assert.assertNotNull(saved.getId());

        List<MancalaJogo> list  = this.gameRepository.findAll();

        Assert.assertEquals(1, list.size());
    }

    @Test
    public void testLoadingKalahaInstances() {

    }
}
