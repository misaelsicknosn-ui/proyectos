<template>
  <div class="container mt-5">
    <div class="row titulo">
      <div class="col-md-7 position-relative">
        <h1>Sistema para Control de Citas</h1>
        <br/>
        <br/>
        <app-alertas 
          :mensaje="mensajeAlerta" 
          :tipo="tipoAlerta" 
          :mostrar="mostrarAlerta"
        />
        <h3 class=" position-absolute bottom-0 mb-0">Citas</h3>
      </div>
      <div class="col-md-5 ng-star-inserted">
        <app-ficha nombre-completo="Juan Carlos Perez" perfil="Administrador" />
      </div>
      <hr class="red" />
    </div>
    <div class="row">
      <div class="col-md-6">
        <h4>Lista de citas</h4>
      </div>
      <div class="col-md-6 text-end">
        <button type="button" class="btn btn-primary" data-bs-toggle="modal" @click="abriModalNuevo" data-bs-target="#crearEditarCitaModal">
        + Crear Cita
      </button>
      </div>
    </div>
    <div class="row">
      <div v-if="citas.length === 0">
        No hay citas registradas
      </div>

      <table v-else class="table table-striped">
        <tr>
          <th>#</th>
          <th>Fecha y hora</th>
          <th>Paciente</th>
          <th>Tipo de cita</th>
          <th>Nombre médico</th>
          <th>Número cita</th>
          <th>Estatus</th>
          <th>Acciones</th>
        </tr>
        <tr v-for="(cita, index) in citas" :key="index">
          <td>{{ index+1 }}</td>
          <td>{{ cita.fechaHoraCita || 'Sin fecha' }}</td>
          <td>{{ cita.paciente }}</td>
          <td>{{ cita.tipoCita }}</td>
          <td>{{ cita.nombreMedico }}</td>
          <td>{{ cita.numeroCita }}</td>
          <td>{{ cita.estatus }}</td>
          <td>
            <button v-if="cita.estatus=='Activa'" type="button" class="btn btn-primary btn-sm" data-bs-toggle="modal" @click="abriModalEditar(cita.numeroCita)" data-bs-target="#crearEditarCitaModal">Editar</button>
            &nbsp;
            <button v-if="cita.estatus=='Activa'" type="button" class="btn btn-secondary btn-sm" @click="abriModalEliminar(cita.numeroCita)">Eliminar</button>
          </td>
        </tr>
      </table>
    </div>

    <!-- Modal Crear -->
    <div class="modal fade" id="crearEditarCitaModal" v-show="modalNuevoEditarAbierto" tabindex="-1" aria-labelledby="crearCitaLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="crearCitaLabel">{{modalNuevoEditarAbierto}}{{tipoModal==='NUEVA'?'Nueva Cita':'Editar Cita'}} {{ numeroCitaAux }}</h5>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Cerrar"></button>
          </div>
          <div class="modal-body">
            <form id="formCrearCita" @submit.prevent="guardarCita">
              <div class="mb-3">
                <label for="fechaHoraCita" class="form-label">Fecha y Hora</label>
                <input v-model="fechaHoraCita" type="datetime-local" class="form-control" id="fechaHoraCita" required>
              </div>
              <div class="mb-3">
                <label for="paciente" class="form-label">Paciente</label>
                <input v-model="paciente" type="text" class="form-control" id="paciente" placeholder="Nombre del paciente" required :disabled="tipoModal=='EDITAR'">
              </div>
              <div class="mb-3">
                <label for="tipoCita" class="form-label">Tipo de Cita</label>
                <select v-model="tipoCita" class="form-select" id="tipoCita" required>
                  <option value="">Seleccione...</option>
                  <option value="Consulta">Consulta</option>
                  <option value="Revisión">Servicio</option>
                  <option value="Revisión">Tratamiento</option>
                  <option value="Revisión">Revisión</option>
                  <option value="Urgencia">Urgencia</option>
                </select>
              </div>
              <div class="mb-3">
                <label for="nombreMedico" class="form-label">Nombre del Médico</label>
                <input v-model="nombreMedico" type="text" class="form-control" id="nombreMedico" placeholder="Dr./Dra. ..." required>
              </div>
            </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
            <button type="submit" form="formCrearCita" class="btn btn-primary">Guardar</button>
          </div>
        </div>
      </div>
    </div>

    <!-- Modal Eliminar -->
    <div 
      class="modal fade" 
      :class="{ show: modalEliminarAbierto }" 
      tabindex="-1" 
      role="dialog"
      :style="{ display: modalEliminarAbierto ? 'block' : 'none' }"
    >
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title">Confirmación</h5>
            <button type="button" class="btn-close" @click="cerrarModal"></button>
          </div>
          <div class="modal-body">
            <p>¿Seguro que quieres eliminar la cita {{ numeroCitaAux }}?</p>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" @click="cerrarModal">
              Cerrar
            </button>
            <button type="button" class="btn btn-primary" @click="eliminarCita">
              Aceptar
            </button>
          </div>
        </div>
      </div>
    </div>

    <!-- Backdrop separado -->
    <div 
      v-if="modalEliminarAbierto" 
      class="modal-backdrop fade show"
      style="z-index: 1040"
    ></div>
  </div> 
</template>

<script setup lang="ts">
  import { ref, onMounted } from 'vue'
  import axios from 'axios'
  import AppAlertas from '../components/AppAlertas.vue'
  import AppFicha from '../components/AppFicha.vue'

  // Interfaces
  interface CitaDetalle {
    fechaHoraCita: string | null
    paciente: string
    tipoCita: string
    nombreMedico: string
    numeroCita: string
    estatus: string
  }

  interface CitasResponse {
    codigo: number
    mensaje: string
    tipoAlerta: string
    citaDetalleDtoList: CitaDetalle[]
  }

  // Variables reactivas
  const user = ref('Administrador')
  const mensajeAlerta = ref('')
  const tipoAlerta = ref('')
  const mostrarAlerta = ref(false)
  const modalEliminarAbierto = ref(false)
  const modalNuevoEditarAbierto = ref(false)
  const citas = ref<CitaDetalle[]>([])

  // Estado del formulario
  const fechaHoraCita = ref('')
  const paciente = ref('')
  const tipoCita = ref('')
  const nombreMedico = ref('')
  const tipoModal = ref('')
  const numeroCitaAux = ref('')


  // Métodos
  function logout() {
    localStorage.removeItem('auth')
    window.location.href = '/' // Si usas router.push, importa useRouter
  }

  function abriModalNuevo() {
     tipoModal.value = 'NUEVA'
  }

  function abriModalEditar(numeroCitaAuxsss:string) {
     tipoModal.value = 'EDITAR'
     console.log('Cita a editar es:'+numeroCitaAuxsss);
     citas.value.forEach(elem =>{
      if(elem.numeroCita === numeroCitaAuxsss){
        console.log('Cita encontrada');
        fechaHoraCita.value = elem.fechaHoraCita;
        paciente.value = elem.paciente;
        tipoCita.value = elem.tipoCita;
        nombreMedico.value = elem.nombreMedico;
        numeroCitaAux.value = elem.numeroCita;
      }      
     });
  }

  function abriModalEliminar(numeroCitaAuxsss:string) {
    modalEliminarAbierto.value = true
    console.log('Cita a editar es:'+numeroCitaAuxsss);
     citas.value.forEach(elem =>{
      if(elem.numeroCita === numeroCitaAuxsss){
        numeroCitaAux.value = elem.numeroCita;
      }      
     });
  }

  function cerrarModal() {
    modalEliminarAbierto.value = false
  }

  async function cargarCitas() {
    try {
      const resp = await axios.get<CitasResponse>("http://localhost:9002/citas/crud/obtener-todas")
      citas.value = resp.data.citaDetalleDtoList
    } catch (error) {
      console.error("Error al obtener citas", error)
    }
  }

  const guardarCita = async () => {
    try {
      var fechaFormateada = formatearFecha(fechaHoraCita.value);
      if(tipoModal.value==='NUEVA'){
        const nuevaCita = {
          fechaHoraCita: fechaFormateada,
          paciente: paciente.value,
          tipoCita: tipoCita.value,
          nombreMedico: nombreMedico.value
        }

        const resp = await axios.post("http://localhost:9002/citas/crud/guardar", nuevaCita)
        console.log("Cita guardada:", resp.data)
      } else if(tipoModal.value==='EDITAR'){
        const actualizarCita = {
          numeroCita: numeroCitaAux.value,
          fechaHoraCita: fechaFormateada,
          paciente: paciente.value,
          tipoCita: tipoCita.value,
          nombreMedico: nombreMedico.value
        }

        const resp = await axios.put("http://localhost:9002/citas/crud/actualizar", actualizarCita)
        console.log("Cita actualizada:", resp.data)
      }

      modalNuevoEditarAbierto.value = false;

      cargarCitas();
      
      // Limpia formulario
      fechaHoraCita.value = ''
      paciente.value = ''
      tipoCita.value = ''
      nombreMedico.value = ''

    } catch (error) {
      console.error("Error al guardar cita", error)
      alert("Error al guardar la cita")
    }
  }

  async function eliminarCita() {
    try {
      const eliminarCita = {
        numeroCita: numeroCitaAux.value
      }

      const resp = await axios.delete("http://localhost:9002/citas/crud/eliminar", {
         data: eliminarCita
      } as any)
      console.log("Cita eliminada:", resp.data)

      modalEliminarAbierto.value = false

      cargarCitas();
      
      // Limpia formulario
      numeroCitaAux.value = ''

    } catch (error) {
      console.error("Error al guardar cita", error)
      alert("Error al eliminar la cita")
    }
  }

  function formatearFecha(fechaIso: string): string {
    const fecha = new Date(fechaIso)
    
    const dia = String(fecha.getDate()).padStart(2, '0')
    const mes = String(fecha.getMonth() + 1).padStart(2, '0')
    const anio = fecha.getFullYear()

    const horas = String(fecha.getHours()).padStart(2, '0')
    const minutos = String(fecha.getMinutes()).padStart(2, '0')
    const segundos = String(fecha.getSeconds()).padStart(2, '0')

    return `${dia}/${mes}/${anio} ${horas}:${minutos}:${segundos}`
  }


  // Cargar al montar el DOM
  onMounted(() => {
    cargarCitas()
  })
</script>