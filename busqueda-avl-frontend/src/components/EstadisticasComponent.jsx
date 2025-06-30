import { useEffect, useState } from "react"
import ServicioBusqueda from "../services/ServicioBusqueda";

function EstadisticasComponent({ documentos }) {
    const [estadistica, setEstadistica] = useState(null);

    useEffect(() => {
        ServicioBusqueda.getEstadisticas().then(respuesta => {
            setEstadistica(respuesta.data)
            console.log(respuesta.data)
        }).catch(error => {
            console.log(error);
        })
    }, [documentos])

    return (
        <section className="text-[#1f1f1f] dark:text-[#DADCE0]">
            <div className="md:pt-5 pt-10" />
            <div className="md:border-l-1 border-[#d8d8d8] dark:border-[#292C32] grid grid-cols-12">
                <div className="col-span-12 pl-5">
                    <strong className="text-[#1f1f1f] dark:text-[#DADCE0] text-2xl">Estadisticas</strong>
                    <p className="text-sm text-[#474747] dark:dark:text-[#DADCE0]">Estadísticas de búsqueda</p>
                </div>
                <div className="col-span-12 pt-5 pl-5 text-[#1f1f1f] dark:dark:text-[#DADCE0] pr-5 md:text-xs lg:text-base text-base">
                    <div className="border-t-1 border-[#d8d8d8] dark:border-[#292C32] pt-5">
                        {!estadistica ? (
                            <>
                                <p>Sin datos</p>
                            </>
                        ) : (

                            <>
                                <p>Busquedas Totales: <span> {estadistica.busquedasTotales}</span></p>
                                <p>Comparaciones Promedio: <span>{estadistica.comparacionesPromedio}</span></p>
                                <p>Tiempo Promedio Milisegundos: <span>{estadistica.tiempoPromedioMilisegundos}</span></p>
                            </>
                        )
                        }
                    </div>
                </div>
            </div>
        </section>
    )
}
export default EstadisticasComponent