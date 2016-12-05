<nav class="navbar navbar-fixed-top navbar-dark bg-inverse">
    <a class="navbar-brand" href="/">
        <i class="fa fa-code-fork" aria-hidden="true"></i>
        Developers' Blog
    </a>

    <ul class="nav navbar-nav float-xs-right">
        <#attempt>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <img src="${user.avatar}" alt="avatar" class="avatar">
                    ${user.name}
                </a>
                <div class="dropdown-menu" aria-labelledby="supportedContentDropdown">
                    <a class="dropdown-item" href="/posts">
                        <span class="fa fa-eye pr-1"></span>View Posts
                    </a>
                    <#if user.isAdmin>
                        <h6 class="dropdown-header">Admin</h6>
                        <a class="dropdown-item" href="/admin/publish-posts">
                            <span class="fa fa-send pr-1"></span>
                            Publish Posts
                        </a>
                        <a class="dropdown-item" href="/admin/trust-users">
                            <span class="fa fa-user-circle pr-1"></span>
                            Trust Users
                        </a>
                        <div class="dropdown-divider"></div>
                    </#if>
                    <a class="dropdown-item" href="/logout">
                        <span class="fa fa-sign-out pr-1"></span>Logout
                    </a>
                </div>
            </li>
        <#recover>
            <li class="nav-item
                <#if contextPath == "/login">
                    active
                </#if>
            ">
                <a class="nav-link" href="/login">Login</a>
            </li>
        </#attempt>
    </ul>
</nav>