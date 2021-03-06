package coffeeshop.DAO.impl;

import coffeeshop.DAO.IBillDetailDao;
import coffeeshop.DTO.BillDetail;
import coffeeshop.Util.*;
import lombok.extern.log4j.Log4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j
public class BillDetailDao implements IBillDetailDao {

    Connection conn = null;
    CallableStatement cs = null;
    ResultSet rs = null;
    private BaseMessage response;

    public BillDetailDao(DbUtil dbUtil) {
        conn = dbUtil.getInstance().getConnection();
    }

    @Override
    public List<BillDetail> getAll(int bill_id) {
        List<BillDetail> list = new ArrayList<>();
        String sql = "{CALL sp_getBillDetailByBillId(?)}";

        try {
            cs = conn.prepareCall(sql);
            cs.setInt(1, bill_id);
            rs = cs.executeQuery();

            while (rs.next()) {
                BillDetail billDetail = new BillDetail(
                        rs.getInt("bill_id"),
                        rs.getInt("product_id"),
                        rs.getInt("amount"),
                        rs.getNString("product_name"),
                        rs.getFloat("product_price")
                );

                list.add(billDetail);
            }

            response = new MessageResponse<>(Constant.SUCCESS_RESPONSE, "Thành công", list);
            log.info(Common.createMessageLog(bill_id, response, "getAll"));
        } catch (SQLException e) {
            response = new BaseMessage(Constant.ERROR_RESPONSE, e.getMessage());
            log.error(Common.createMessageLog(bill_id, response, "getAll"));
        } finally {
            rs = null;
            cs = null;
        }

        return list;
    }

    @Override
    public Map<String, Object> create(BillDetail billDetail) {
        Map<String, Object> output = new HashMap<>();
        String sql = "{CALL sp_insertBillDetail(?, ?, ?, ?, ?)}";

        try {
            cs = conn.prepareCall(sql);
            cs.setInt(1, billDetail.getBill_id());
            cs.setInt(2, billDetail.getProduct_id());
            cs.setInt(3, billDetail.getAmount());
            cs.registerOutParameter(4, Types.BIT);
            cs.registerOutParameter(5, Types.NVARCHAR);
            cs.execute();

            output.put("status", cs.getBoolean(4));
            output.put("message", cs.getNString(5));

            response = new MessageResponse<>(cs.getBoolean(4), cs.getNString(5), output);
            if (cs.getBoolean(4)) {
                log.info(Common.createMessageLog(billDetail, response, "create"));
            } else {
                log.error(Common.createMessageLog(billDetail, response, "create"));
            }
        } catch (SQLException e) {
            response = new BaseMessage(Constant.ERROR_RESPONSE, e.getMessage());
            log.error(Common.createMessageLog(billDetail, response, "create"));
        } finally {
            cs = null;
        }

        return output;
    }

    @Override
    public BillDetail read(int id) {
        return null;
    }

    @Override
    public Map<String, Object> update(BillDetail billDetail) {
        Map<String, Object> output = new HashMap<>();
        String sql = "{CALL sp_updateBillDetail(?, ?, ?, ?, ?)}";

        try {
            cs = conn.prepareCall(sql);
            cs.setInt(1, billDetail.getBill_id());
            cs.setInt(2, billDetail.getProduct_id());
            cs.setInt(3, billDetail.getAmount());
            cs.registerOutParameter(4, Types.BIT);
            cs.registerOutParameter(5, Types.NVARCHAR);
            cs.execute();

            output.put("status", cs.getBoolean(4));
            output.put("message", cs.getNString(5));

            response = new MessageResponse<>(cs.getBoolean(4), cs.getNString(5), output);
            if (cs.getBoolean(4)) {
                log.info(Common.createMessageLog(billDetail, response, "update"));
            } else {
                log.error(Common.createMessageLog(billDetail, response, "update"));
            }
        } catch (SQLException e) {
            response = new BaseMessage(Constant.ERROR_RESPONSE, e.getMessage());
            log.error(Common.createMessageLog(billDetail, response, "update"));
        } finally {
            cs = null;
        }

        return output;
    }

    @Override
    public Map<String, Object> delete(BillDetail billDetail) {

        Map<String, Object> output = new HashMap<>();
        String sql = "{CALL sp_deleteBillDetail(?, ?, ?, ?)}";

        try {
            cs = conn.prepareCall(sql);
            cs.setInt(1, billDetail.getBill_id());
            cs.setInt(2, billDetail.getProduct_id());
            cs.registerOutParameter(3, Types.BIT);
            cs.registerOutParameter(4, Types.NVARCHAR);
            cs.execute();

            output.put("status", cs.getBoolean(3));
            output.put("message", cs.getNString(4));

            response = new MessageResponse<>(cs.getBoolean(2), cs.getNString(3), output);
            if (cs.getBoolean(2)) {
                log.info(Common.createMessageLog(billDetail, response, "delete"));
            } else {
                log.error(Common.createMessageLog(billDetail, response, "delete"));
            }
        } catch (SQLException e) {
            response = new BaseMessage(Constant.ERROR_RESPONSE, e.getMessage());
            log.error(Common.createMessageLog(billDetail, response, "delete"));
        } finally {
            cs = null;
        }

        return output;
    }
}
