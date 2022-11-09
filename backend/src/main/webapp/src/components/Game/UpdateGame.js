const { default: ViewGameForm } = require("./ViewGameForm");

const UpdateGame = () => {
    const game = Object.fromEntries(new URLSearchParams(window.location.search));

    return (
        <ViewGameForm update={true} gamename={game.nome} typegame={game.tipoJogo} gamequantity={game.quantidadeJogadores} gameId={game.idJogo} />
    )
}

export default UpdateGame
