import Lottie from "lottie-react";
import animacionPaloma from "../assets/Lottie/Paloma.json";
import animacionDinosaurio from "../assets/Lottie/DinosaurioBailando.json";
import { useEffect, useState } from "react";
import ServicioBusqueda from '../services/ServicioBusqueda';

function ListaArchivos({ documentos, setDocumentos, palabraBuscada, direccion, existeRuta }) {

    const abrirDocumento = (direccion) => {
        const d = { direccion }
        ServicioBusqueda.abrirDocumento(d).then(resultado => {
            console.log("Abriendo documento")
        }).catch(error => {
            console.log("Error al abrir documento")
            console.log(error);
        })
    }


    if (!existeRuta?.direccion) {
        return (
            <section>
                <div className="text-[#1f1f1f] dark:text-[#DADCE0] pt-8 text-sm">
                    <p className="pb-4 text-2xl font-bold">Aún no has cargado la ruta de los documentos</p>
                    <p className="pb-3">Sigue estos pasos para empezar:</p>
                    <ul className="list-disc list-inside">
                        <li>Ingresa la ruta de los documentos.</li>
                        <li>Asegúrate de que haya archivos .docx dentro.</li>
                        <li>Cuando estés listo, carga la ruta y comenzamos la búsqueda.</li>
                    </ul>
                    <Lottie animationData={animacionPaloma} loop={true} className="w-100 h-auto" />
                </div>
            </section>
        );
    }


    return (
        <section>
            {!palabraBuscada ? (
                <div className="text-[#1f1f1f] dark:text-[#DADCE0] pt-8 text-sm">
                    <p className="pb-4 text-2xl font-bold">Ya puedes realizar las busquedas</p>
                    <Lottie animationData={animacionDinosaurio} loop={true} className="w-100 h-auto" />
                </div>
            ) : (
                !documentos || documentos.length === 0 ? (
                    <div className="text-[#1f1f1f] dark:text-[#DADCE0] pt-10 text-sm">
                        <p className="pb-4">No se han encontrado resultados para tu búsqueda <span className="font-bold">{"(" + palabraBuscada + ")"}</span>.</p>
                        <p className="pb-3">Sugerencias:</p>
                        <ul className="list-disc list-inside">
                            <li>Asegúrate de que todas las palabras estén escritas correctamente.</li>
                            <li>Prueba diferentes palabras clave.</li>
                            <li>Prueba palabras clave más generales.</li>
                            <li>Prueba menos palabras clave.</li>
                        </ul>
                        <div>
                            <img src="/Pescador.svg" alt="Pescador sin resultados" className="w-140 h-auto" />
                        </div>
                    </div>
                ) : (
                    documentos.map((doc) => (
                        <div key={doc.ruta} className="p-3 max-w-3xl">
                            <div className="transition-all">
                                <span className="cursor-pointer"
                                    onClick={() => abrirDocumento(doc.ruta)}
                                >
                                    <div className="text-sm text-gray-600 dark:text-[#DADCE0] flex items-start">

                                        <div className="w-7 h-7 p-1 bg-white rounded-full flex items-center justify-center mt-1">
                                            <svg className="w-5 h-5">
                                                <use xlinkHref="/sprite.svg#word_alt" />
                                            </svg>
                                        </div>


                                        <div className="xl:pl-3 pl-2">
                                            <span className="text-[#202124] dark:text-[#DADCE0]">Documento</span><br />
                                            <p
                                                className="text-xs text-[#474747] dark:text-[#DADCE0] truncate block max-w-50"
                                                title={doc.ruta}>
                                                {doc.ruta}
                                            </p>
                                        </div>
                                    </div>
                                    <p
                                        className="block mt-2 text-lg sm:text-xl font-medium text-[#1a0dab] dark:text-[#7EAAFF] hover:underline"
                                    >
                                        {doc.titulo}
                                    </p>
                                </span>
                                <p className="text-sm sm:text-base text-[#474747] dark:text-[#CDCDCD]">
                                    {doc.descripcion}
                                </p>
                            </div>
                        </div>
                    ))
                )
            )}
        </section>
    );
}

export default ListaArchivos;
