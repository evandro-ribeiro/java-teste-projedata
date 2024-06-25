import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Principal {

    public static void main(String[] args) {
        // 3.1 - Inserir todos os funcionários
        List<Funcionario> funcionarios = new ArrayList<>();

        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));

        funcionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"));

        funcionarios
                .add(new Funcionario("Caio", LocalDate.of(1961, 05, 02), new BigDecimal("9836.14"), "Coordenador"));

        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));

        funcionarios
                .add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"));

        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));

        funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"));

        funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"));

        funcionarios
                .add(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));

        funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente"));

        // 3.2 - Remover o funcionário "João" da lista
        funcionarios.removeIf(funcionario -> funcionario.getNome().equals("João"));

        // 3.3 - Imprimir todos os funcionários
        System.out.println("Lista de Funcionários:");
        funcionarios.forEach(funcionario -> {
            System.out.println(funcionario.toString());
        });

        // 3.4 - Aumentar salário em 10%
        funcionarios.forEach(funcionario -> {
            BigDecimal salarioAtualizado = funcionario.getSalario().multiply(new BigDecimal("1.10"));
            funcionario.setSalario(salarioAtualizado);
        });

        // 3.5 - Agrupar funcionários por função em um MAP
        Map<String, List<Funcionario>> funcionariosPorFuncao = funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));

        // 3.6 - Imprimir funcionários agrupados por função
        System.out.println("\nFuncionários agrupados por função:");
        funcionariosPorFuncao.forEach((funcao, lista) -> {
            System.out.println("\nFunção: " + funcao);
            lista.forEach(funcionario -> System.out.println(funcionario.toString()));
        });

        // 3.7 - Imprimir funcionários que fazem aniversário nos meses 10 e 12
        int[] mesesAniversario = { 10, 12 };
        System.out.println("\nFuncionários que fazem aniversário nos meses 10 e 12:");
        funcionarios.stream()
                .filter(funcionario -> {
                    int mesAniversario = funcionario.getDataNascimento().getMonthValue();
                    for (int mes : mesesAniversario) {
                        if (mesAniversario == mes)
                            return true;
                    }
                    return false;
                })
                .forEach(funcionario -> System.out.println(funcionario.toString()));

        // 3.8 - Encontrar funcionário com maior idade
        Funcionario maisVelho = funcionarios.stream()
                .min((f1, f2) -> f1.getDataNascimento().compareTo(f2.getDataNascimento()))
                .orElse(null);
        if (maisVelho != null) {
            long idadeMaisVelho = LocalDate.now().getYear() - maisVelho.getDataNascimento().getYear();
            System.out.println("\nFuncionário mais velho:");
            System.out.println("Nome: " + maisVelho.getNome() + ", Idade: " + idadeMaisVelho + " anos");
        }

        // 3.9 - Ordenar funcionários por ordem alfabética
        System.out.println("\nLista de funcionários em ordem alfabética:");
        funcionarios.stream()
                .sorted((f1, f2) -> f1.getNome().compareTo(f2.getNome()))
                .forEach(funcionario -> System.out.println(funcionario.getNome()));

        // 3.10 - Calcular total dos salários dos funcionários
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMinimumFractionDigits(2);
        nf.setMaximumFractionDigits(2);
        BigDecimal totalSalarios = funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("\nTotal dos salários dos funcionários: R$ " + nf.format(totalSalarios).toString());

        // 3.11 - Imprimir quantos salários mínimos ganha cada funcionário (considerando
        // R$1212.00 como mínimo)
        System.out.println("\nQuantos salários mínimos cada funcionário ganha:");
        BigDecimal salarioMinimo = new BigDecimal("1212.00");
        funcionarios.forEach(funcionario -> {
            @SuppressWarnings("deprecation")
            BigDecimal salarioEmMinimos = funcionario.getSalario().divide(salarioMinimo, 2, BigDecimal.ROUND_DOWN);
            System.out.println(funcionario.getNome() + ": " + salarioEmMinimos + " salários mínimos");
        });
    }
}
