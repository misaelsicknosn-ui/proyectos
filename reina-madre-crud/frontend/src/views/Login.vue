<template>
  <div class="container mt-5" style="max-width: 400px;">
    <h3 class="text-center">Iniciar Sesión</h3>
    <form @submit.prevent="login">
      <div class="mb-3">
        <label>Email</label>
        <input v-model="email" type="email" class="form-control" value="administrador@reinamadre.mx" required>
      </div>
      <div class="mb-3">
        <label>Contraseña</label>
        <input v-model="password" type="password" class="form-control" value="Welcome1" required>
      </div>
      <button class="btn btn-primary w-100">Entrar</button>
    </form>
    <p v-if="error" class="text-danger mt-2">{{ error }}</p>
  </div>
</template>

<script>
export default {
  data() {
    return {
      email: '',
      password: '',
      error: ''
    }
  },
  methods: {
    login() {
      if (this.email === 'administrador@reinamadre.mx' && this.password === 'Welcome1') {
        localStorage.setItem('auth', 'true')
        this.$router.push('/welcome')
      } else {
        this.error = 'Credenciales incorrectas'
      }
    }
  }
}
</script>