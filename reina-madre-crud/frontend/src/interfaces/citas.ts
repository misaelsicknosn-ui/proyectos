export interface CitaDetalle {
  fechaHoraCita: string | null
  paciente: string
  tipoCita: string
  nombreMedico: string
  numeroCita: string
}

export interface CitasResponse {
  codigo: number
  mensaje: string
  tipoAlerta: string
  citaDetalleDtoList: CitaDetalle[]
}