function toggleTab( tab )
{
    if ( tab.className === "off" )
    {
        var encendida = 0;
        var pestanias = tab.parentNode.childNodes;
        for ( var i = 0; i < pestanias.length; i ++ )
        {
            if ( pestanias[i] === tab )
            {
                encendida = i;
            }
            if ( pestanias[i].className === "on" )
            {
                pestanias[i].className = "off";
            }
        }
        tab.className="on";

        var divs = document.getElementById("contenedor").childNodes;
        for ( var i = 0; i < divs.length; i ++ )
        {
            if ( divs[i].className === "on")
            {
                divs[i].className = "off";
            }
        }
        divs[ encendida - 2].className = "on";
    }                        
}