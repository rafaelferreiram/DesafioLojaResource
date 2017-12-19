package br.com.desafio.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.desafio.dao.ProdutoDao;
import br.com.desafio.domain.Produto;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/adicionaproduto")
public class LojaServlet extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// buscando os parametros no request
		String nome = request.getParameter("nome");
		String tipo = request.getParameter("tipo");
		String p = request.getParameter("preco");
		String porc = request.getParameter("taxa");

		String str = porc.replaceAll("%", "");
		double taxa = Double.parseDouble(str);

		String strp = p.replace(".", "");

		String tratamentov = strp.replaceAll(",", ".");
		
		double preco = Double.parseDouble(tratamentov);
		// monta um objeto contato
		Produto produto = new Produto();

		produto.setNome(nome);
		produto.setTipo(tipo);
		produto.setPreco(preco);
		produto.setTaxa(taxa);

		// salva contato
		ProdutoDao dao;
		try {
			dao = new ProdutoDao();
			dao.create(produto);

		} catch (Exception e) {
			e.printStackTrace();
		}
		 
		String redirectURL = "http://localhost:8088/DesafioLojaResource/Upload.jsp";
		response.sendRedirect(redirectURL);

	}

}
