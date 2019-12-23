<template>
    <main>
        <h2>Formulário para Cadastro de Novo Aluno</h2>
        <div>
            <img class="icone_casa" src="../assets/home.png" @click="carregarListagem()">
            <p>Página Inicial</p>

            <form>
                <label for="matricula">Matricula:</label>
                <input type="number" class="matricula" name="matricula" maxlength="50" @keypress="apenasNumeros" v-model="aluno.matricula">
                <br>

                <label for="nome">Nome: </label>
                <input class="nome" type="text" name="nome" maxlength="50" @keypress="apenasChar" v-model="aluno.pessoa.name">
                <br>

                <label for="ano">Ano de Ingresso: </label>
                <input type="number" class="ano" name="ano" maxlength="4" @keypress="apenasNumeros" v-model="aluno.anoDeEntrada">
                <br>

                <input class="confirmarBtn" type="button" value="Connfirmar" @click="salvar()">
                <input class="cancelarBtn" type="button" value="Cancelar" @click="carregarListagem()">
            </form>
        </div>
    </main>
</template>

<script>
export default {
    props: {
        aluno: {type: Object},
    },
    methods: {
        /**
         * Faz uma requisição do tipo post ou put dependendo do id
         * e carrega a página de listagem.
         */
        salvar(){
            this.$emit('limparCamposVazios')
            const metodo = this.aluno.id ? 'put' : 'post'
            this.$http[metodo]('/formulario', this.aluno)
                        .then(() => {
                            this.carregarListagem()
                        }).catch(error  => {
                            this.$emit('ativaAlerta', error)
                            
                        })
        },

        //Retorna para a página de listagem
        carregarListagem(){
            this.$router.push('/listagem')
            this.limpar()
        },

        /** Limpa os dados do objeto aluno e 
        *   emite o evento para desativar o erro.
        */
        limpar(){
            this.$emit('desativaAlerta', null)
            this.$emit('alteraAluno', 
            {
                id: 0,
                matricula: '',
                anoDeEntrada: '',
                pessoa: {
                    nome: ''
                }
            }) 
        },

        // Método que restringe a entrada apenas para números
        apenasNumeros(event){
            const tecla = String.fromCharCode(event.which)
            const regex = /[0-9]/
            if(!(tecla.match(regex))){
                event.preventDefault()
            }
        },
        // Método que restringe a entrada de dados apenas para letras e espaços
        apenasChar(event){ 
            var tecla = String.fromCharCode(event.which)
            var regex = /[a-zA-ZáàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ ]/
            if(! (tecla.match(regex) )){
                event.preventDefault()
            }
        }
    }
}
</script>

<style>
main{
    padding-left: 20px;
}

main div{
    padding-left: 30px;

}
main .icone_casa{
    width: 3%;
}

main div p{
    display: inline-block;
    padding-left: 20px;
}

main div form label, main div form input{
    display: inline-block;
    margin-top: 15px;

}
main div form{
    position: relative;
}
.ano, .matricula, .nome{
    position: absolute;
    border-radius: 5px;
    border: 2px solid #ccc;
    left: 150px;
}

.matricula{
    width: 120px;
}
.ano{
    width: 70px;
}
.nome{
    width: 300px;
}
.confirmarBtn{
    width: 90px;
    background-color: green;
    padding: 5px;
    color: white;
    border: 1px solid black;
    border-radius: 5px;
    margin-left: 50px;
}
.cancelarBtn{
    width: 90px;
    background-color: red;
    padding: 5px;
    color: white;
    border: 1px solid black;
    border-radius: 5px;
    margin-left: 50px;
}
</style>