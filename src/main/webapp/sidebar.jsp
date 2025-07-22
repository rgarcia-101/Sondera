<%--TODO redo, works poorly for responsive design--%>

<div class="container-fluid" style="min-height: 100vh">
    <div class="row vh-100">
        <div class="col-md-auto bg-dark sticky-top">
            <div class="d-flex flex-md-column flex-row flex-nowrap bg-dark align-items-center sticky-top">
                <ul class="nav nav-pills flex-column mb-auto">
                    <li>
                        <a href="home" class="nav-link text-white">
                            <object data="images/house-light.svg" width="16" height="16"></object>
                            Home
                        </a>
                    </li>
                    <li>
                        <a href="notes" class="nav-link text-white">
                            <object data="images/stickies-light.svg" width="16" height="16"></object>
                            Notes
                        </a>
                    </li>
                    <li>
                        <a href="todos" class="nav-link text-white">
                            <object data="images/list-light.svg" width="16" height="16"></object>
                            Todos
                        </a>
                    </li>
                    <li>
                        <a href="bookmarks" class="nav-link text-white">
                            <object data="images/bookmark-light.svg" width="16" height="16"></object>
                            Bookmarks
                        </a>
                    </li>
                    <li>
                        <a href="dates" class="nav-link text-white">
                            <object data="images/calendar-light.svg" width="16" height="16"></object>
                            Dates
                        </a>
                    </li>
                </ul>
            </div>
        </div>
        <div class="col-sm p-3" style="min-height: 100%">
            <!-- content from view -->