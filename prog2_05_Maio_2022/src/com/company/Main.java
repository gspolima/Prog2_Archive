package com.company;

import java.sql.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/javadb", "root", "Estacio@123");

            System.out.printf("Digite um nome para o Aluno: ");
            Scanner scanner = new Scanner(System.in);
            String nome = scanner.nextLine();

            Statement st = conexao.createStatement();
            String query = "INSERT INTO ALUNO (NOME) VALUES (?)";
            PreparedStatement preparedStatement = conexao.prepareStatement(query);
            preparedStatement.setString(1, nome);

            boolean isResultSet = preparedStatement.execute();

            if(isResultSet == false) {
                System.out.println("Aluno inserido");
                ResultSet resultados = st.executeQuery("SELECT * FROM ALUNO");

                while(resultados.next()) {
                    System.out.println(resultados.getInt("id") + " " + resultados.getString("nome"));
                }
            }
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
}

/*
CREATE DATABASE javaDB;
use javadb;
CREATE TABLE ALUNO (
    ID INT NOT NULL AUTO_INCREMENT,
    NOME VARCHAR(50),

    PRIMARY KEY (ID)
);
 */
