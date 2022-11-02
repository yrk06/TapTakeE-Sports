const { default: ViewOrgsForm } = require("./ViewOrgsForm");

const UpdateOrg = () => {
    const orgs = Object.fromEntries(new URLSearchParams(window.location.search));

    console.log(orgs)
    return (
        <ViewOrgsForm update={true} orgname={orgs.nome} typegame={orgs.localOrg} gamequantity={orgs.dataCriacao} gameId={orgs.idOrganizacao} />
    )
}

export default UpdateOrg
