import Vue from 'vue'
import Router from 'vue-router'
import Formulario from './components/Formulario.vue'
import ListaAlunos from './components/ListaAlunos.vue'

Vue.use(Router)


export default new Router({
    // Modo history definido para gerar uma url mais
    // amigável para o usuário, sem #.
    mode: 'history',

    // Define os componentes que serão chamados 
    // de acordo com cada caminho da url.
    routes: [{
        path: '/listagem',
        component: ListaAlunos
    },{
        path: '/formulario',
        component: Formulario
    },
    
    // Se for chmado um caminho diferente, retorna para a listagem
    {path: '*', redirect: '/listagem'}]
})