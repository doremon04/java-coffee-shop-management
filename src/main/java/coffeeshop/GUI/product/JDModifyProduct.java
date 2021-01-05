/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coffeeshop.GUI.product;

import coffeeshop.DTO.Category;
import coffeeshop.DTO.Product;
import coffeeshop.DAO.impl.CategoryDao;
import coffeeshop.DAO.impl.ProductDao;
import coffeeshop.Util.Common;
import coffeeshop.Util.DbUtil;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Minh
 */
public final class JDModifyProduct extends javax.swing.JDialog {

    Product product;
    DbUtil dbUtil;
    CallbackProductModify callback;
    List<Category> categories = new ArrayList<>();
    CategoryDao categoryDao;
    ProductDao productDao;

    interface CallbackProductModify {

        public void actionProductModify();
    }

    /**
     * Creates new form JDCategoryCreate
     *
     * @param parent
     * @param modal
     * @param dbUtil
     * @param product
     * @param callback
     */
    public JDModifyProduct(java.awt.Frame parent, boolean modal, DbUtil dbUtil, CallbackProductModify callback, Product product) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        this.callback = callback;
        this.dbUtil = dbUtil;
        this.categoryDao = new CategoryDao(dbUtil);
        this.productDao = new ProductDao(dbUtil);

        if (!Common.isNullOrEmpty(product)) {
            lblTitle.setText("Sửa đổi sản phẩm");
            btnModify.setText("Sửa đổi");
            this.product = product;
            loadingData();
        }

        lblNameError.setVisible(false);
        lblPriceError.setVisible(false);
        loadCategory();
    }

    public void loadCategory() {
        categories = categoryDao.getAll(null);
        DefaultComboBoxModel<Category> dcbm = new DefaultComboBoxModel<>();
        categories.forEach(category -> {
            dcbm.addElement(category);
        });

        cboCategory.setModel(dcbm);
    }

    public void loadingData() {
        txtName.setText(product.getName());
        txtPrice.setText(String.valueOf(this.product.getPrice()));
        rdoActive.setSelected(product.getStatus());
        rdoNonActive.setSelected(product.getStatus() == false);
        categories.stream().filter(category -> (Objects.equals(category.getId(), product.getCategory_id()))).forEachOrdered(category -> {
            cboCategory.setSelectedItem(category);
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();
        lblName = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        lblStatus = new javax.swing.JLabel();
        rdoActive = new javax.swing.JRadioButton();
        rdoNonActive = new javax.swing.JRadioButton();
        btnModify = new javax.swing.JButton();
        lblPrice = new javax.swing.JLabel();
        txtPrice = new javax.swing.JTextField();
        lblCategory = new javax.swing.JLabel();
        cboCategory = new javax.swing.JComboBox<>();
        lblNameError = new javax.swing.JLabel();
        lblPriceError = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("CẬP NHẬT SẢN PHẨM");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        lblTitle.setFont(new java.awt.Font("Segoe UI Semibold", 0, 36)); // NOI18N
        lblTitle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/img/icons8_product_50px_2.png"))); // NOI18N
        lblTitle.setText("THÊM MỚI SẢN PHẨM");

        lblName.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        lblName.setText("Tên sản phẩm");

        lblStatus.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        lblStatus.setText("Trạng thái");

        rdoActive.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rdoActive);
        rdoActive.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        rdoActive.setSelected(true);
        rdoActive.setText("Hoạt động");

        rdoNonActive.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rdoNonActive);
        rdoNonActive.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        rdoNonActive.setText("Không hoạt động");
        rdoNonActive.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoNonActiveActionPerformed(evt);
            }
        });

        btnModify.setBackground(new java.awt.Color(0, 204, 106));
        btnModify.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnModify.setForeground(new java.awt.Color(255, 255, 255));
        btnModify.setText("Thêm mới");
        btnModify.setBorderPainted(false);
        btnModify.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnModify.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModifyActionPerformed(evt);
            }
        });

        lblPrice.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        lblPrice.setText("Giá ");

        lblCategory.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        lblCategory.setText("Danh mục");

        lblNameError.setForeground(new java.awt.Color(240, 71, 71));
        lblNameError.setText("Không được để trống");

        lblPriceError.setForeground(new java.awt.Color(240, 71, 71));
        lblPriceError.setText("Không được để trổng");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtName)
                    .addComponent(lblPrice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtPrice, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblCategory, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cboCategory, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 427, Short.MAX_VALUE)
                        .addComponent(btnModify, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblNameError, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblPriceError, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(rdoActive, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdoNonActive, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNameError)
                .addGap(18, 18, 18)
                .addComponent(lblPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPriceError)
                .addGap(18, 18, 18)
                .addComponent(lblCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdoActive)
                    .addComponent(rdoNonActive))
                .addGap(35, 35, 35)
                .addComponent(btnModify, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rdoNonActiveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoNonActiveActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoNonActiveActionPerformed

    private void btnModifyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModifyActionPerformed
        String name = (String) txtName.getText().trim();
        float price = Float.parseFloat(txtPrice.getText());
        int category_id = ((Category) cboCategory.getSelectedItem()).getId();
        boolean status = (boolean) rdoActive.isSelected();
        boolean validate = true;

        if (name.equals("")) {
            txtName.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(240, 71, 71)),
                    BorderFactory.createEmptyBorder(5, 8, 5, 8)));
            lblName.setForeground(new Color(240, 71, 71));
            lblNameError.setVisible(true);
            validate = false;
        }

        if (txtPrice.getText().trim().equals("")) {
            txtPrice.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(240, 71, 71)),
                    BorderFactory.createEmptyBorder(5, 8, 5, 8)));
            lblPrice.setText("Không được để trống");
            lblPrice.setForeground(new Color(240, 71, 71));
            lblPriceError.setVisible(true);
            validate = false;
        } else if (price <= 0) {
            txtPrice.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(240, 71, 71)),
                    BorderFactory.createEmptyBorder(5, 8, 5, 8)));
            lblPrice.setText("Giá bạn nhập phải là một số thực dương!");
            lblPrice.setForeground(new Color(240, 71, 71));
            lblPriceError.setVisible(true);
            validate = false;
        }

        if (validate == true) {
            lblNameError.setVisible(false);
            lblPriceError.setVisible(false);
            try {
                product = new Product();
                product.setName(name);
                product.setPrice(price);
                product.setCategory_id(category_id);
                product.setStatus(status);

                if (Common.isNullOrEmpty(this.product)) {
                    Map<String, Object> result = productDao.create(product);

                    if ((boolean) result.get("status") == true) {
                        JOptionPane.showMessageDialog(null, "Thêm sản phẩm thành công!");
                        callback.actionProductModify();
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Thêm sản phẩm thất bại, lỗi: " + result.get("message") + "!");
                    }
                } else {
                    product.setId(this.product.getId());
                    Map<String, Object> result = productDao.update(product);
                    if ((boolean) result.get("status") == true) {
                        JOptionPane.showMessageDialog(null, "Sửa sản phẩm thành công!");
                        callback.actionProductModify();
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Sửa sản phẩm thất bại, lỗi: " + result.get("message") + "!");
                    }
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

    }//GEN-LAST:event_btnModifyActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnModify;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<Category> cboCategory;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblCategory;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblNameError;
    private javax.swing.JLabel lblPrice;
    private javax.swing.JLabel lblPriceError;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JRadioButton rdoActive;
    private javax.swing.JRadioButton rdoNonActive;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtPrice;
    // End of variables declaration//GEN-END:variables
}
