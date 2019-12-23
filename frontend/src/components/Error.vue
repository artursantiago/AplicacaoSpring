<template>
    <span class="alert"> 
        {{ error.response.data }}
        <button @click="desativaAlerta">X</button>
    </span>
</template>

<script>
export default {
    props:{
        error: {type: Error}
    },
    methods: {
        // Emite um evento com o valor null para ser definido na variável error no App.vue 
        desativaAlerta() {
            this.$emit('desativaAlerta', null)
        }
    }
}
</script>

<style>
.alert {
    font-size: 18px;
    color: #a94442;
    background-color: #f2dede;
    border-color: #ebccd1;
    padding: 15px;
    border: 1px solid transparent;
    border-radius: 4px;
    display: flex;
    justify-content: space-between;
}
.alert button{
    font-size: 18px;
    color: #a94442;
    background-color: #f2dede;
    border: 1px solid transparent;
    border-radius: 4px;
}
</style>