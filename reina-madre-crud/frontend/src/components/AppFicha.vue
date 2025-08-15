<template>
  <div class="usuario">
    <p>
      {{ nombreCompleto }} | {{ perfil }}
      <a class="text-decoration-underline" @click="logout">Salir</a>
    </p>
  </div>
</template>

<script>
export default {
  name: 'AppFicha',
  props: {
    nombreCompleto: {
      type: String,
      default: "Juan Carlos Perez"
    },
    perfil: {
      type: String,
      default: 'Administrador'
    }
  },
  methods: {
    logout() {
      localStorage.removeItem('auth')
      this.$router.push('/')
    }
  }
}
</script>
