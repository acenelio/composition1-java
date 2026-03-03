package app;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import entities.Department;
import entities.HourContract;
import entities.Worker;
import entities.WorkerLevel;

public class Program {

	void main() {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		IO.print("Entre o nome do departamento: ");
		String departmentName = sc.nextLine();
		
		Department dept = new Department(departmentName);
		
		IO.println("Entre os dados do trabalhador:");
		IO.print("Nome: ");
		String workerName = sc.nextLine();
		IO.print("Nivel: ");
		WorkerLevel workerLevel = WorkerLevel.valueOf(sc.nextLine());
		IO.print("Salario base: ");
		double baseSalary = sc.nextDouble();
		
		Worker worker = new Worker(workerName, workerLevel, baseSalary, dept);
		
		IO.print("Quantos contratos esse trabalhador tem? ");
		int n = sc.nextInt();
		
		for (int i=1; i<=n; i++) {
			IO.println("Entre com os dados do contrato #" + i + ":");
			IO.print("Data (DD/MM/YYYY): ");
			LocalDate contractDate = LocalDate.parse(sc.next(), fmt);
			IO.print("Valor por hora: ");
			double valuePerHour = sc.nextDouble();
			IO.print("Duracao (horas): ");
			int hours = sc.nextInt();
			HourContract contract = new HourContract(contractDate, valuePerHour, hours);
			worker.addContract(contract);
		}
		
		IO.println();
		IO.print("Entre o ano e mes para calcular o ganho (MM/YYYY): ");
		String monthAndYear = sc.next();
		
		int month = Integer.parseInt(monthAndYear.substring(0, 2));
		int year = Integer.parseInt(monthAndYear.substring(3));
		
		IO.println("Nome: " + worker.getName());
		IO.println("Departamento: " + worker.getDepartment().getName());
		IO.println("Ganho para " + monthAndYear + ": " + String.format("%.2f", worker.income(year, month)));
		
		sc.close();
	}
}