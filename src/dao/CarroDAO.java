package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.carro;

public class CarroDAO {
    
    private Connection con;

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }

    public CarroDAO(Connection con) {
        this.con = con;
    }

    public String inserir(carro carro){
        String sql = "insert into carro(placa,cor,descricao) values(?,?,?)";
        try{
            PreparedStatement ps = getCon().prepareStatement(sql);
            ps.setString(1, carro.getPlaca());
            ps.setString(2, carro.getCor());
            ps.setString(3, carro.getDescricao());
            if (ps.executeUpdate() > 0){
                return "inserido com sucesso";
            } else {
                return "Erro ao inserir";
            }
        } catch (SQLException e){
            return e.getMessage();
        }
    }

    public String alterar(carro carro){
        String sql = "update carro ";
        sql += "set cor = ?, descricao = ? ";
        sql += "where placa = ?";
        try{
            PreparedStatement ps = getCon().prepareStatement(sql);
            ps.setString(3, carro.getPlaca());
            ps.setString(1, carro.getCor());
            ps.setString(2, carro.getDescricao());
            if (ps.executeUpdate() > 0){
                return "Alterado com sucesso";
            } else {
                return "Erro ao alterar";
            }
        } catch (SQLException e){
            return e.getMessage();
        }
    }   

    public String excluir(carro carro){
        String sql = "delete from carro ";
        sql += "where placa = ?";
        try{
            PreparedStatement ps = getCon().prepareStatement(sql);
            ps.setString(1, carro.getPlaca());
            if (ps.executeUpdate() > 0){
                return "Excluido com sucesso";
            } else {
                return "Erro ao excluir";
            }
        } catch (SQLException e){
            return e.getMessage();
        }
    }

    public ArrayList<carro> listarTodos(){
        String sql = "select * from carro";
        ArrayList<carro> listaCarro = new ArrayList<carro>();
        try{
            PreparedStatement ps = getCon().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    carro cb = new carro();
                    cb.setPlaca(rs.getString(1));
                    cb.setCor(rs.getString(2));
                    cb.setDescricao(rs.getString(3));
                    listaCarro.add(cb);
                }
                return listaCarro;
            } else {
                return null;
            }
        } catch (SQLException e){
            return null;
        }
    }
}