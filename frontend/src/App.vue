<template>
<div class="container">
  <header class="cabecalho">
    <h1>Universidade Equipe Orbitais</h1>
  </header>
  <!-- Componente que exibe a mensagem de erro
       dependendo do conteúdo das variavel error.-->
  <Error  :error="error" v-if="this.error != null"
          @desativaAlerta="desativaAlerta($event)"/>

  <!-- router-view define componente que será usado a partir da url
       envia a propridade aluno para seus componentes filhos
       e recebe os valores de aluno e de erro dos componentes filhos-->
  <router-view  :aluno="aluno" 
                @alteraAluno="aluno = $event"
                @desativaAlerta="desativaAlerta($event)"
                @ativaAlerta="ativaAlerta($event)"
                @limparCamposVazios="limparCamposVazios"/>

</div>
</template>

<script>
import Error from './components/Error'

export default {
  // Define que o componente Error poderá ser acessado pela palavra Error.
  components: {Error},
  data(){
    return {
      // Variável que representa o erro lançado pelo backend.
      error : null,
      // Objeto aluno que está sendo manipulado no momento.
      aluno: {
        id: 0,
        matricula: '',
        anoDeEntrada: '',
        pessoa: {
          name: ''
        }
      }
    }
  },
  methods: {
    // Método que recebe o evento de ativaAlerta do Formulario 
    // e define-o como a variável error.
    ativaAlerta(e){
      this.error = e
    },
    // Método que recebe o evento desativaAlerta do componente Error 
    // e define-o como a variável error.
    desativaAlerta(e){
      this.error = e
    },
    //Limpa espaços desnecessários no campo nome
    limparCamposVazios(){
      this.aluno.pessoa.name = this.aluno.pessoa.name.trim()
    }
  }
}
</script>

<style>
  .container{
    font-family: sans-serif;
    width: 600px;
    margin: 0px auto;
  }

  .cabecalho h1{
      border: 1px solid black;
      background-color: blue;
      color: white;
      text-align: center;
      padding: 30px;
  }
</style>
