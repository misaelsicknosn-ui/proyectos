<template>
  <div v-if="mostrar" :class="['alert', alertClass]" role="alert">
    {{ mensaje }}
  </div>
</template>

<script>
export default {
  name: 'AppAlertas',
  props: {
    mensaje: {
      type: String,
      required: true
    },
    tipo: {
      type: String,
      default: 'primary' // Bootstrap: primary, success, danger, warning...
    },
    mostrar: {
      type: Boolean,
      default: false
    }
  },
  computed: {
    alertClass() {
      return `alert-${this.tipo}`
    }
  }
}
</script>