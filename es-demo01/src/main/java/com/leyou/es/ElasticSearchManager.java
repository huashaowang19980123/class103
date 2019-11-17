package com.leyou.es;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.net.InetAddress;

public class ElasticSearchManager {
    TransportClient client=null;
    @Before
    public void initClient() throws Exception {
        client=new PreBuiltTransportClient(Settings.EMPTY)
                .addTransportAddress(new TransportAddress(InetAddress.getByName("127.0.0.1"),9300));
    }
    @Test
    public void testQuery(){
        QueryBuilder qb= QueryBuilders.termQuery("goodsName","小米");
        SearchResponse searchResponse=client.prepareSearch("heima")
                .setQuery(qb)
                .get();
        long totalHits=searchResponse.getHits().getTotalHits();
        System.out.println(totalHits);
        SearchHit[] searchHits=searchResponse.getHits().getHits();
        for (SearchHit searchHit : searchHits) {
            String sourceAsString = searchHit.getSourceAsString();
            System.out.println(sourceAsString);
        }

    }
    @After
    public void end(){
        client.close();
    }
	 @Test
    public void lisi(){
       System.out.println("李四");
    }
}
