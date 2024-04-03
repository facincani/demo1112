package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

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
