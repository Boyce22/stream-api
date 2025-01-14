package br.com.stream.api.services;

import br.com.stream.api.enums.CategoriaAnime;
import br.com.stream.api.models.Anime;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class StreamPredicate {
    private static final int QUANTIDADE_MAXIMA_ANIMES = 7;

    private static final String MENSAGEM_ANIMES_DISPONIVEIS = "Animes disponíveis, caso houver iremos filtrar pelos animes da fantasia.";

    private static final String MENSAGEM_ANIMES_FILTRADOS = "Animes filtrados:";

    private static final String MENSAGEM_NENHUM_ANIME_FANTASIA = "Infelizmente não foi possível obter nenhum anime com o gênero de fantasia.";

    private final Predicate<Anime> filtroAnimesFantasia = anime ->
            CategoriaAnime.FANTASIA.getDescricao().equals(anime.getCategoria());

    public void imprimirAnimes() {
        List<Anime> animes = Stream.generate(Anime::criar)
                .limit(QUANTIDADE_MAXIMA_ANIMES)
                .toList();

        gerarLinhaDivisoria();
        System.out.println(MENSAGEM_ANIMES_DISPONIVEIS);
        gerarLinhaDivisoria();

        exibirListaDeAnimes(animes);

        List<Anime> animesFantasias = animes.stream()
                .filter(filtroAnimesFantasia)
                .toList();

        if (animesFantasias.isEmpty()) {
            System.out.println(MENSAGEM_NENHUM_ANIME_FANTASIA);
        } else {
            System.out.println(MENSAGEM_ANIMES_FILTRADOS);
            gerarLinhaDivisoria();
            exibirListaDeAnimes(animesFantasias);
        }
    }

    private void exibirListaDeAnimes(List<Anime> listaDeAnimes) {
        listaDeAnimes.stream()
                .map(Anime::toString)
                .forEach(System.out::println);

        gerarLinhaDivisoria();
    }

    private void gerarLinhaDivisoria() {
        final char CARACTERE_DIVISORIA = '=';
        final int TAMANHO_LINHA = 50;
        System.out.println(String.valueOf(CARACTERE_DIVISORIA).repeat(TAMANHO_LINHA));
    }
}