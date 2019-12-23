<template>
<main class="lista-alunos">
    <h2>Lista de Alunos</h2>
        <div>
            <img class="icone_cadastro" src="../assets/plus_icon.png" @click="cadastrarAluno()">
            <p>Cadastrar novo Aluno</p>
            <table>
                <thead>
                    <tr>
                        <th>Matricula</th>
                        <th>Nome</th>
                        <th>Ano de Ingresso</th>
                        <th>Ações</th>
                    </tr>
                </thead>

                <tbody>
                    <tr v-for="aluno in alunos" :key="aluno.id">
                        <td>{{ aluno.matricula }}</td>
                        <td>{{ aluno.pessoa.name }}</td>
                        <td>{{ aluno.anoDeEntrada }}</td>
                        <td>
                            <img src="../assets/edit-icon.jpg" @click="preEdit(aluno.id)">
                            <img src="../assets/trash_icon.png" @click="deletar(aluno.id)">
                        </td>
                    </tr>
                </tbody>

            </table>
        </div>
</main>
</template>

<script>
export default {
    props: {
        aluno: Object
    },
    data(){
        return{
            // Lista dos alunos recuperados do banco de dados.
            alunos: [],
        }
    },
    // Chamará a função listarAlunos sempre que a página for carregada
    created(){
        this.listarAlunos()
    },
    methods: {
        // Recupera os dados do aluno que será editado e carrega a página de cadastro.
        preEdit(id) {
            this.$http.get(`/listagem/${id}`)
                            .then(res => { 
                                this.$emit('alteraAluno', res.data)
                                this.cadastrarAluno() 
                                })
        },
        /**Faz um requisição para deletar o aluno com o id
         * selecionado e chama a função listarAlunos novamente.
        */
        deletar(id){
            this.$http.delete(`/listagem/${id}`)
                             .then(() => this.listarAlunos())
        },
        // Faz uma requisição para recuperar todos os alunos do banco de dados.
        listarAlunos(){
            this.$http.get('/listagem')
                        .then(res => { this.alunos = res.data })
        },
        // Carrega a página de cadastro
        cadastrarAluno(){
            this.$router.push('/formulario')
        }
    }
}
</script>

<style>
.lista-alunos{
    padding-left: 20px;
}

.lista-alunos div{
    padding-left: 30px;

}

.lista-alunos img{
    width: 20px;
}

.lista-alunos p{
    display: inline-block;
    padding-left: 15px;
}

table th, td{
    font-weight: normal;
    text-align: left;
    padding-right: 30px;
    padding-top: 5px;
}
</style>