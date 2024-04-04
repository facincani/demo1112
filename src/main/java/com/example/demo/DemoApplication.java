package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {


	private final JdbcTemplate jdbcTemplate;

	public DemoApplication(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {


	}

	private static void openCsv() throws IOException {
		Reader reader = Files.newBufferedReader(Paths.get("D:\\Document\\GitHub\\demo1112\\pessoas.csv"));
		CsvToBean<Pessoa> csvToBean =
				new CsvToBeanBuilder<Pessoa>(reader)
						.withType(Pessoa.class)
						.withIgnoreLeadingWhiteSpace(true)
						.build();

		List<Pessoa> pessoas = csvToBean.parse();
		pessoas.forEach(System.out::println);
	}

	private static void lerCsvNaRaca() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("D:\\Document\\GitHub\\demo1112\\pessoas.csv"));

		String linha;

		br.readLine();

		List<Pessoa> pessoas = new ArrayList<>();

		while ((linha = br.readLine()) != null){
			String[] valores = linha.split(",");
			pessoas.add(
					new Pessoa(
							Integer.parseInt(valores[0]),
							valores[1],
							Integer.parseInt(valores[2]
							)
					)
			);
		}

		pessoas.forEach(System.out::println);
	}


	private void delete() {
		String sql = "delete from pessoas where id = ?";
		jdbcTemplate.update(sql, 5);
	}

	private void update() {
		String sql = "update pessoas set nome = ? where id = ?";
		jdbcTemplate.update(sql, "Glauber", 6);
	}

	private void insert() {
		String sql = "INSERT INTO pessoas (id, nome, idade) VALUES (?, ?, ?)";
		jdbcTemplate.update(sql, 6, "Glauber", 25);
	}

	private void selectPessoas() {
		String sql = "SELECT * FROM PESSOAS";

		RowMapper<Pessoa> rowMapper = ((rs, rowNum) -> new Pessoa(
				rs.getInt("ID"),
				rs.getString("NOME"),
				rs.getInt("IDADE")
		));

		List<Pessoa> listaPessoas = jdbcTemplate.query(sql, rowMapper);

		listaPessoas.forEach(p -> {
			System.out.println(p.getNome());
		});
	}

}
