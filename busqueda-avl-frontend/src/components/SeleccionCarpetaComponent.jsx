import ServicioBusqueda from "../services/ServicioBusqueda";

function SeleccionCarpetaComponent({ setDocumentos, direccion, setDireccion, existeRuta, setExisteRuta }) {

    const asignarRuta = (e) => {
        console.log("Asignando Ruta");
        e.preventDefault();

        if (!direccion) {
            setDireccion(null);
            console.log("sin ruta");
            return;
        }

        const r = { direccion };

        ServicioBusqueda.setRutaArchivo(r).then(() => {
            console.log("Ruta enviada");
            console.log(r)

            ServicioBusqueda.getRutaArchivo().then((respuesta) => {
                setExisteRuta(respuesta.data);
                console.log("Guardando ruta en backend: ")
                console.log(existeRuta)
            });
            
        }).catch(error => {
            console.log("error al enviar ruta: ", error);
        });
    };

    return (
        <section className="grid grid-cols-12 border-b-1 border-[#d8d8d8] dark:border-[#292C32]">
            <div className="col-span-1" />
            <div className="items-center col-span-11 xl:pl-20 sm:pl-30 pl-0">
                <div className="w-9/10">

                    <form onSubmit={(e) => asignarRuta(e)} className="flex items-center gap-2">
                        <label className="text-sm sm:text-base text-[#393b3c] dark:text-[#cacaca]">
                            Ruta:
                        </label>
                        <input type="text" id="entrada_ruta"
                            value={direccion}
                            placeholder="C:/Carpeta"
                            className="pl-2 py-2.5 w-full relative text-[#70757a] placeholder-[#70757a] dark:text-[#a0a0a0] dark:placeholder-[#777777] focus:outline-none"
                            onChange={(e) => setDireccion(e.target.value)}
                        />
                    </form>
                </div>
            </div>
        </section>
    )
}

export default SeleccionCarpetaComponent