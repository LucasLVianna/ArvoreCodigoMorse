# Código Morse com Árvore Binária — TDE 2

Programa de console em Java que guarda o alfabeto Morse (A a Z) em uma árvore binária. Pelo menu é possível decodificar Morse para texto, codificar texto para Morse, ver a árvore desenhada no console e ouvir o código Morse gerado.

Não usa bibliotecas externas, só a biblioteca padrão do Java.

## Como funciona

A árvore começa com uma raiz vazia. Cada letra é inserida seguindo o seu código Morse, símbolo por símbolo:

- ponto (`.`) desce para o filho da **esquerda**;
- traço (`-`) desce para o filho da **direita**.

Se um nó do caminho ainda não existe, ele é criado na hora. A letra é gravada no último nó do caminho. Por exemplo, `A` (`.-`) fica em raiz → esquerda → direita.

A árvore é montada só com essas inserções, usando a tabela de A a Z.

**Decodificação:** para cada letra em Morse, o programa parte da raiz e vai descendo para a esquerda ou para a direita conforme cada símbolo. A letra do nó onde ele para é a letra decodificada.

**Codificação:** para cada letra do texto, o programa procura essa letra na árvore, começando pela raiz. Enquanto desce, anota `.` quando vai para a esquerda e `-` quando vai para a direita. O caminho até a letra é o código Morse dela.

## Funcionalidades

O menu roda em laço até o usuário escolher sair:

```
===== Código Morse =====
1 - Decodificar Morse para texto
2 - Codificar texto para Morse
3 - Mostrar a árvore
4 - Sair
```

- **Decodificar:** transforma uma sequência em Morse no texto correspondente.
- **Codificar:** transforma um texto em Morse. Letras minúsculas são aceitas e convertidas para maiúsculas.
- **Mostrar a árvore:** desenha a árvore deitada no console.
- **Tocar o som:** depois de codificar, o programa pergunta `Deseja ouvir o resultado? (s/n)`. Se a resposta for `s` ou `sim`, ele toca o código como um tom de 600 Hz. A unidade de tempo é 100 ms: o ponto dura 1 unidade e o traço 3. Entre símbolos da mesma letra há 1 unidade de pausa, entre letras 3 e entre palavras 7. Se o computador não tiver som disponível, aparece uma mensagem e o programa continua normalmente.

Entradas inválidas mostram uma mensagem de erro e o programa volta ao menu. São tratados os casos de símbolo diferente de `.` e `-`, código Morse que não existe na árvore, caractere que não é letra e opção de menu inválida.

## Formato da entrada

- As letras em Morse são separadas por um espaço.
- As palavras são separadas por ` / ` (barra com espaços).

Exemplos:

| Entrada | Saída |
| --- | --- |
| `... --- ...` | `SOS` |
| `.... --- .-.. .- / -- ..- -. -.. ---` | `HOLA MUNDO` |

Na codificação basta digitar o texto normalmente, com as palavras separadas por espaço. `Hola Mundo` vira `.... --- .-.. .- / -- ..- -. -.. ---`.

Só são aceitas as letras de A a Z, sem acentos, números ou pontuação.

## Exemplo da árvore impressa

Saída real da opção 3. A raiz fica à esquerda, o filho do traço (`-`) aparece acima do pai e o filho do ponto (`.`) aparece abaixo:

```
            ┌── (-) O
        ┌── (-) M
        │   │   ┌── (-) Q
        │   └── (.) G
        │       └── (.) Z
    ┌── (-) T
    │   │       ┌── (-) Y
    │   │   ┌── (-) K
    │   │   │   └── (.) C
    │   └── (.) N
    │       │   ┌── (-) X
    │       └── (.) D
    │           └── (.) B
(raiz)
    │           ┌── (-) J
    │       ┌── (-) W
    │       │   └── (.) P
    │   ┌── (-) A
    │   │   └── (.) R
    │   │       └── (.) L
    └── (.) E
        │   ┌── (-) U
        │   │   └── (.) F
        └── (.) I
            │   ┌── (-) V
            └── (.) S
                └── (.) H
```

## Estrutura de arquivos

```
src/
  Principal.java
  NoMorse.java
  ArvoreMorse.java
  Decodificador.java
  Codificador.java
  ImpressorDeArvore.java
  ReprodutorDeSom.java
  MenuDoConsole.java
```

| Classe | Responsabilidade |
| --- | --- |
| `Principal` | Cria os objetos, carrega o alfabeto na árvore e inicia o menu. |
| `NoMorse` | Nó da árvore: guarda a letra (pode ser vazia) e os filhos `esquerda` e `direita`. |
| `ArvoreMorse` | Guarda a raiz e insere as letras com `inserirLetra`. Monta a tabela de A a Z com `carregarAlfabetoPadrao`. Busca uma letra pelo código com `buscarLetra` e o código de uma letra com `buscarCaminho`. |
| `Decodificador` | Transforma Morse em texto com `decodificarFrase` e valida os símbolos e os códigos. |
| `Codificador` | Transforma texto em Morse com `codificarFrase`, usando o caminho de cada letra na árvore. |
| `ImpressorDeArvore` | Desenha a árvore deitada no console. |
| `ReprodutorDeSom` | Toca o código Morse com `tocarCodigoMorse`, usando apenas `javax.sound.sampled`. |
| `MenuDoConsole` | Mostra o menu, lê as entradas do usuário e chama as outras classes. |

## Como compilar e rodar

Requisito: **Java 17 ou superior**.

Na raiz do projeto:

```
javac -d out src/*.java
java -cp out Principal
```

## Autores

- Nome 1
- Nome 2
- Nome 3
