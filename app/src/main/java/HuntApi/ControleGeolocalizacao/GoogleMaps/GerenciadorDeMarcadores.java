package HuntApi.ControleGeolocalizacao.GoogleMaps;

import android.util.Log;

import com.akexorcist.googledirection.DirectionCallback;
import com.akexorcist.googledirection.GoogleDirection;
import com.akexorcist.googledirection.constant.AvoidType;
import com.akexorcist.googledirection.model.Direction;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.List;

import HuntApi.Model.CordenadaGeografica;
import HuntApi.Model.QuestGeolocalizada;
import HuntApi.Model.Usuario;

/**
 * Created by Iury on 6/23/2016.
 */
public class GerenciadorDeMarcadores {

    private GoogleMap mapa;

    public GoogleMap addQuestMarcadoresMapaUsuario(GoogleMap map, Usuario usuario) {
        if(usuario != null) {
            for (QuestGeolocalizada quest : usuario.getMinhasQuests()) {
                Log.d("usuario","chego aqui " + quest.getNome());
                map = adicionarQuestPontos(map,quest);
            }
        } else {
            Log.d("usuario","usuario nome: "+ usuario.getName());
        }

        return map;
    }

    public GoogleMap addQuestMarcadores(GoogleMap map, List<QuestGeolocalizada> questGeolocalizadas) {
        for(QuestGeolocalizada quest : questGeolocalizadas) {
            map = adicionarQuestPontos(map,quest);
        }
        map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                CordenadaGeografica cordenadaAtual = getGoogleServiceClient.getPosicaoAtual();
                LatLng posicaoAtual = new LatLng(cordenadaAtual.getLat(), cordenadaAtual.getLon());
                GoogleDirection.withServerKey("AIzaSyCnRSH_4g45uAVNKePEI8H1Zz7abNJ1dFw")
                        .from(posicaoAtual)
                        .to(marker.getPosition())
                        .avoid(AvoidType.FERRIES)
                        .avoid(AvoidType.HIGHWAYS)
                        .execute(new DirectionCallback() {
                            @Override
                            public void onDirectionSuccess(Direction direction, String rawBody) {
                                if(direction.isOK()) {
                                    // Do something
                                } else {
                                    // Do something
                                }
                            }

                            @Override
                            public void onDirectionFailure(Throwable t) {
                                // Do something
                            }
                        });
                return false;
            }
        });
        return map;
    }

    public void addQuestProcimasMap(float raio, CordenadaGeografica cordenadaGeografica) {

    }

    public GoogleMap adicionarQuestPontos(GoogleMap map, QuestGeolocalizada quest) {
        LatLng latLng = new LatLng(quest.getCordenada().getLat(),quest.getCordenada().getLon());
        map.addMarker(new MarkerOptions().position(latLng)
                                         .title(quest.getNome())
                                         .flat(true));

        return map;
    }
}
