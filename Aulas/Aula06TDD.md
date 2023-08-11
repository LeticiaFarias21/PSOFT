# Aula 06

Date: August 11, 2023 8:00 AM
Estágio: primerio
Text: “Explicação ”Lab-02 TDD

******TDD******

Controllador inclui um ou vários servços

Serviço, atomica e funcional, cada serviço produz uma unica coisa

**************************Padrão de URL para poder evoluir o sistema, o endpoint**************************

![Untitled](Aula%2006%20ef64cd31899247c88a753dd85d5419f7/Untitled.png)

********************************Criando os Tests********************************

![Untitled](Aula%2006%20ef64cd31899247c88a753dd85d5419f7/Untitled%201.png)

**********************************************************************Seguir padrão nomenclatura de testes do slide**********************************************************************

****************DisplayName****************

![Untitled](Aula%2006%20ef64cd31899247c88a753dd85d5419f7/Untitled%202.png)

**********Mock********** 

![Untitled](Aula%2006%20ef64cd31899247c88a753dd85d5419f7/Untitled%203.png)

**************Nested************** 

![Untitled](Aula%2006%20ef64cd31899247c88a753dd85d5419f7/Untitled%204.png)

**********************DTO, sempre começa com o nome da entidade**********************

![Untitled](Aula%2006%20ef64cd31899247c88a753dd85d5419f7/Untitled%205.png)

- O que coincidir pode juntar

bibliotecas importadas

![Untitled](Aula%2006%20ef64cd31899247c88a753dd85d5419f7/Untitled%206.png)

Atributos com o JSONPropety

![Untitled](Aula%2006%20ef64cd31899247c88a753dd85d5419f7/Untitled%207.png)

******Atributo URL TEMPLATE****** 

![Untitled](Aula%2006%20ef64cd31899247c88a753dd85d5419f7/Untitled%208.png)

**********************************O teste é dividido em Arrange, Act e Assert**********************************

Arrange

![Untitled](Aula%2006%20ef64cd31899247c88a753dd85d5419f7/Untitled%209.png)

Act, atenção nos parenteses e no alinhamento 

![Untitled](Aula%2006%20ef64cd31899247c88a753dd85d5419f7/Untitled%2010.png)

Assert - no lab fazer o necessário, na aula ta mostrando outromos modos de asserts

![Untitled](Aula%2006%20ef64cd31899247c88a753dd85d5419f7/Untitled%2011.png)

Controller para fazer a requisição funcionar

![Untitled](Aula%2006%20ef64cd31899247c88a753dd85d5419f7/Untitled%2012.png)

- Cria a interface para controllar as versões

![Untitled](Aula%2006%20ef64cd31899247c88a753dd85d5419f7/Untitled%2013.png)

- nova classe

![Untitled](Aula%2006%20ef64cd31899247c88a753dd85d5419f7/Untitled%2014.png)

![Untitled](Aula%2006%20ef64cd31899247c88a753dd85d5419f7/Untitled%2015.png)

- implementaçao da terceira camada REPO

![Untitled](Aula%2006%20ef64cd31899247c88a753dd85d5419f7/Untitled%2016.png)

![Untitled](Aula%2006%20ef64cd31899247c88a753dd85d5419f7/Untitled%2017.png)

- Como nao usa jpa tem o volatil

![Untitled](Aula%2006%20ef64cd31899247c88a753dd85d5419f7/Untitled%2018.png)

que implementa a interface

![Untitled](Aula%2006%20ef64cd31899247c88a753dd85d5419f7/Untitled%2019.png)

![Untitled](Aula%2006%20ef64cd31899247c88a753dd85d5419f7/Untitled%2020.png)

Tarefa Repo

![Untitled](Aula%2006%20ef64cd31899247c88a753dd85d5419f7/Untitled%2021.png)

![Untitled](Aula%2006%20ef64cd31899247c88a753dd85d5419f7/Untitled%2022.png)