package entities;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import entities.enums.WorkerLevel;

public class Worker {
	
	private String name;
	private WorkerLevel level;
	private Double baseSalary;
	
	//ASSOCIAÇÕES:
	//informar que dentro de Worker TEM 1 Department
	private Department department;
	
	//1 Worker TEM VARIOS HourContract
	// (como tem varios, é preciso representar isso através de uma lista --> chamada contracts)
	private List <HourContract> contracts = new ArrayList<>(); // a lista ja tem que ser instanciada na declaração do atributo (pois ela é um array)
	//quando existir uma composiçaõ "TEM MUITOS" -que nesse caso é uma lista- eu não posso incluí-lo no construtor (como vamos ver abaixo), simplesmente se inicia a lista vazia (exemplo acima)
	
	public Worker() {
		
	}

	//o construtor com argumentos não vai ser gerado com a lista contracts (pois ela já foi instanciada)
	public Worker(String name, WorkerLevel level, Double baseSalary, Department department) {
		this.name = name;
		this.level = level;
		this.baseSalary = baseSalary;
		this.department = department;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public WorkerLevel getLevel() {
		return level;
	}

	public void setLevel(WorkerLevel level) {
		this.level = level;
	}

	public Double getBaseSalary() {
		return baseSalary;
	}

	public void setBaseSalary(Double baseSalary) {
		this.baseSalary = baseSalary;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public List<HourContract> getContracts() {
		return contracts;
	}
	
	// vai se retirar o setContracts, pois, não é possível recebr uma lista e "trocar" de lista --> o que ocorre é que a lista pode receber ou ter contratos removidos (através das funções criadas abaixo):	

	//METHODS:
	public void addContract(HourContract contract) {
		//vai adicionar na lista contracts o contrato que é passado para a função de argumento
		contracts.add(contract);
	}
	
	public void removeContract(HourContract contract) {
		contracts.remove(contract);
	}
	
	//função que calcula quanto uma pessoa ganhou baseada em um ano e em um mês
	public double income(int year, int month) {
		// salario base + ganho dos contratos daquele mês
		double sum = baseSalary;
		Calendar cal = Calendar.getInstance();
		for (HourContract c : contracts) {
			cal.setTime(c.getDate());
			int c_year = cal.get(Calendar.YEAR);
			int c_month = 1 + cal.get(Calendar.MONTH);
			if ( year == c_year && month == c_month) {
				sum += c.totalValue();
			}
		}
		return sum;
	}

}
