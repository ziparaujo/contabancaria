package conta.controller;

import java.util.ArrayList;

import conta.model.Conta;
import conta.repository.ContaRepository;

public class ContaController implements ContaRepository {
	
	private ArrayList<Conta> listaContas = new ArrayList<Conta>();
	int numero = 0;

	@Override
	public void procurarPorNumero(int numero) {
		var conta = buscarNaCollection(numero);
		
		if(conta != null)
			conta.visualizar();
		else
			System.out.printf("\nA conta número: %d não foi encontrada.", numero);
	}

	@Override
	public void listarTodas() {
		for(var conta : listaContas) {
			conta.visualizar();
		}		
	}

	@Override
	public void cadastrar(Conta conta) {
		listaContas.add(conta);
		System.out.printf("\nA conta de número: %d foi criada com sucesso!",  conta.getNumero());
	}

	@Override
	public void atualizar(Conta conta) {
		var buscaConta = buscarNaCollection(conta.getNumero());
		
		if(buscaConta != null) {
			listaContas.set(listaContas.indexOf(buscaConta), conta);
			System.out.printf("\nA conta número: %d foi atualizada com sucesso!", conta.getNumero());
		} else {
			System.out.printf("\nA conta número: %d não foi encontrada!", conta.getNumero());
		}
		
	}

	@Override
	public void deletar(int numero) {
		var conta = buscarNaCollection(numero);
		
		if(conta != null) {
			if(listaContas.remove(conta) == true)
				System.out.printf("\nA conta número: %d foi deletada com sucesso!", numero);
		} else {
			System.out.printf("\nA conta número: %d não foi encontrada!", numero);
		}
		
	}

	@Override
	public void sacar(int numero, float valor) {
		var conta = buscarNaCollection(numero);
		
		if(conta != null) {
			if(conta.sacar(valor) == true)
				System.out.printf("\nO Saque na conta número: %d foi efetuado com sucesso!", numero);
		} else
			System.out.printf("\nA conta número: %d não foi encontrada!", numero);
		
	}

	@Override
	public void depositar(int numero, float valor) {
		var conta = buscarNaCollection(numero);
		
		if(conta != null) {
			conta.depositar(valor);
			System.out.printf("\nO depósito na conta número: %d foi efetuado com sucesso!", numero);
		} else
			System.out.printf("\nA conta número: %d não foi encontrada ou a conta destino não é uma conta corrente!", numero);
		
	}

	@Override
	public void transferir(int numeroOrigem, int numeroDestino, float valor) {
		var contaOrigem = buscarNaCollection(numeroOrigem);
		var contaDestino = buscarNaCollection(numeroDestino);
		
		if(contaOrigem != null && contaDestino != null) {
			if(contaOrigem.sacar(valor) == true) {
				contaDestino.depositar(valor);
				System.out.println("\nA transferência foi efetuada com sucesso!");
			}
		} else 
			System.out.println("\nA conta de origem e/ou destino não foram encontradas.");
		
	}
	
	public int gerarNumero() {
		return ++numero;
	}

	public Conta buscarNaCollection(int numero) {
		for(var conta : listaContas) {
			if(conta.getNumero() == numero) {
				return conta;
			}			
		}
		
		return null;
	}
}

