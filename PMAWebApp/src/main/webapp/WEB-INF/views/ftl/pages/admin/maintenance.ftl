<#import "/spring.ftl" as spring />
<#assign serverDir><@spring.url '/' /></#assign>
<style>
</style>
<div class="post" id="post-37">
<#include "../topMenu.ftl" />
<a href="${serverDir}/admin/password">Change Password</a><br/>
<a href="">Reset Verification Questions</a>
</div>