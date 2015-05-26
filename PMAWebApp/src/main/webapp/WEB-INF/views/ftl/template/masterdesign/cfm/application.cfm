<cfapplication sessionmanagement="Yes" setclientcookies="Yes" name="Prudent" sessiontimeout="#CreateTimeSpan(0,0,20,0)#">
<CFSET "PMAServer" = "http://bethblineburydesign.com/prudentmanagement"> 
<CFSET "OldPMAServerSecure" = "https://www.prudentmanagement.com/Secure">
<CFSET "PMAServerSecure" = "http://localhost:8888/secure">
<CFSET "AppPath" = "http://localhost:8888">
<CFSET "SecurePath" = "http://localhost:8888/secure">
<!--- <CFSET "AppPath" = "http://Jupiter.interactive.net/prudentmanagement">
<CFSET "SecurePath" = "http://Jupiter.interactive.net/prudentmanagement/secure"> --->