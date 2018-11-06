requirejs.config({
    'baseUrl': '../lib',
    'paths': {
        'leaflet.wms': '../dist/leaflet.wms' //.js'
    }
});

define(['leaflet', 'leaflet.wms'],
function(L, wms) {

var tiledMap = createMap('mapID', false);

function createMap(div, tiled) {
    // Map configuration
    var map = L.map(div);
    map.setView([60.452, 22.278], 10);

    var basemaps = {
        'Basemap': basemap().addTo(map),
    };

    // Add WMS source/layers
    var source = wms.source(
        "https://kartat-mip.turku.fi/geoserver/ows?",
        {
            "format": "image/png",
            "transparent": "true",
            "attribution": "<a href='https://data.lounaistieto.fi/data/dataset/paikallismuseot-varsinais-suomessa/resource/7c096fe4-9ac5-457a-bf39-44e7f1a4ce32'>Lounaistieto</a>",
            "info_format": "text/html",
            "tiled": tiled
        }        
    );

    var layers = {
        'Museo': source.getLayer("mip_julkaisut:Paikallismuseot Varsinais-Suomessa kiinteisto").addTo(map),
    };

    return map;
}

function basemap() {
    // maps.stamen.com
    var attr = 'Map data &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors, ' +
    '<a href="https://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, ' +
    'Imagery © <a href="https://www.mapbox.com/">Mapbox</a>';
    return L.tileLayer("https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token=pk.eyJ1IjoibWFwYm94IiwiYSI6ImNpejY4NXVycTA2emYycXBndHRqcmZ3N3gifQ.rJcFIG214AriISLbB6B5aw", {
        attribution: attr,
        id: 'mapbox.streets'
    });
}

// function blank() {
//     var layer = new L.Layer();
//     layer.onAdd = layer.onRemove = function() {};
//     return layer;
// }

// // Export maps for console experimentation
// return {
//     'maps': {
//         'overlay': overlayMap,
//         'tiled': tiledMap
//     }
//};

});

