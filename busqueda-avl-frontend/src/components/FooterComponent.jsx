function FooterComponent() {
    return (
        <footer className="bg-[#F2F2F2] dark:bg-[#1F2128] grid grid-cols-12 h-screen">
            <div className="sm:col-span-1 col-span-0"/>
            <div className="sm:col-span-11 col-span-12 py-4 xl:px-25 px-0">
                <div className="text-center sm:text-left flex flex-col sm:flex-row text-sm col-span-12 py-2">
                    <span className="dark:text-[#afb0b4] ">Los resultados no están personalizados - </span>
                    <span className="text-[#1a0dab] dark:text-[#7EAAFF] cursor-pointer px-1">Probar con personalización</span>
                </div>
                <div className="text-center sm:text-left flex flex-col sm:flex-row sm:text-sm text-xs sm:border-b-1 dark:border-[#292C32] border-[#dad6d6] col-span-12 py-2">
                    <span className="sm:border-r-1 dark:border-[#292C32] border-[#dad6d6] dark:text-[#838487] p-2">Guatemala</span>
                    <span className="p-2 dark:text-[#c5c6c8]">🔵 San Lorenzo - De tu dispositivo - Actualizar ubicación</span>
                </div>
                <div className="py-2 sm:text-sm text-xs text-[#838487] text-center sm:text-left">
                    <span className="cursor-pointer sm:px-3 px-2">Ayuda</span>
                    <span className="cursor-pointer sm:px-3 px-2">Enviar comentarios</span>
                    <span className="cursor-pointer sm:px-3 px-2">Privacidad</span>
                    <span className="cursor-pointer sm:px-3 px-2">Condiciones</span>
                </div>
            </div>
        </footer>
    )
}
export default FooterComponent