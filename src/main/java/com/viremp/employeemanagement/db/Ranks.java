/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viremp.employeemanagement.db;

import com.viremp.employeemanagement.models.Rank;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Maddy
 */
public class Ranks {

    public static final String TABLE = "ranks";
    public static final String COL_RANK_ID = "rank_id";
    public static final String COL_RANK_NAME = "rank_name";

    public static List<Rank> getAllRanks() throws SQLException {
        List<Rank> ranks = new ArrayList<>();
        ResultSet rs = GenericDB.getByTableName(TABLE, null);
        while (rs.next()) {
            Rank rank = new Rank();
            rank.setRankId(rs.getInt(COL_RANK_ID));
            rank.setRankName(rs.getString(COL_RANK_NAME));
            ranks.add(rank);
        }
        return ranks;
    }
    public static boolean addRank(Rank rank) throws SQLException{
        return GenericDB.insert(TABLE, getValuesMap(rank));
    }
    public static boolean deleteRank(int rankId) throws SQLException{
        return GenericDB.delete(TABLE, COL_RANK_ID, rankId);
    }
    private static Map<String,Object> getValuesMap(Rank rank){
        Map<String,Object> values = new HashMap<>();
        values.put(COL_RANK_ID, rank.getRankId());
        values.put(COL_RANK_NAME, rank.getRankName());
        return values;
    }
}
