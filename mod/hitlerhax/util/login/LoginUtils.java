// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.util.login;

import java.lang.reflect.Field;
import com.mojang.authlib.exceptions.AuthenticationException;
import com.mojang.authlib.exceptions.AuthenticationUnavailableException;
import net.minecraft.util.Session;
import net.minecraft.client.Minecraft;
import com.mojang.authlib.Agent;
import com.mojang.authlib.yggdrasil.YggdrasilUserAuthentication;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import java.net.Proxy;
import mod.hitlerhax.util.Globals;

public class LoginUtils implements Globals
{
    public static final String session;
    
    public static String loginAlt(final String email, final String password) {
        final YggdrasilAuthenticationService authenticationService = new YggdrasilAuthenticationService(Proxy.NO_PROXY, "");
        final YggdrasilUserAuthentication authentication = (YggdrasilUserAuthentication)authenticationService.createUserAuthentication(Agent.MINECRAFT);
        authentication.setUsername(email);
        authentication.setPassword(password);
        String displayText;
        try {
            authentication.logIn();
            try {
                final Field f = Minecraft.class.getDeclaredField(LoginUtils.session);
                f.setAccessible(true);
                f.set(LoginUtils.mc, new Session(authentication.getSelectedProfile().getName(), authentication.getSelectedProfile().getId().toString(), authentication.getAuthenticatedToken(), "mojang"));
                displayText = "Logged [License]: " + LoginUtils.mc.func_110432_I().func_111285_a();
            }
            catch (Exception e) {
                displayText = "Unknown error.";
                e.printStackTrace();
            }
        }
        catch (AuthenticationUnavailableException e3) {
            displayText = "Cannot contact authentication server!";
        }
        catch (AuthenticationException e2) {
            if (e2.getMessage().contains("Invalid username or password.") || e2.getMessage().toLowerCase().contains("account migrated")) {
                displayText = "Wrong password!";
            }
            else {
                displayText = "Cannot contact authentication server!";
            }
        }
        catch (NullPointerException e4) {
            displayText = "Wrong password!";
        }
        return displayText;
    }
    
    public static String getName(final String email, final String password) {
        final YggdrasilAuthenticationService authenticationService = new YggdrasilAuthenticationService(Proxy.NO_PROXY, "");
        final YggdrasilUserAuthentication authentication = (YggdrasilUserAuthentication)authenticationService.createUserAuthentication(Agent.MINECRAFT);
        authentication.setUsername(email);
        authentication.setPassword(password);
        try {
            authentication.logIn();
            return authentication.getSelectedProfile().getName();
        }
        catch (Exception e) {
            return null;
        }
    }
    
    public static void changeCrackedName(final String name) {
        try {
            final Field f = Minecraft.class.getDeclaredField(LoginUtils.session);
            f.setAccessible(true);
            f.set(LoginUtils.mc, new Session(name, "", "", "mojang"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static boolean isNotObfuscated() {
        try {
            return Minecraft.class.getDeclaredField("instance") != null;
        }
        catch (Exception ex) {
            return false;
        }
    }
    
    static {
        session = (isNotObfuscated() ? "session" : "field_71449_j");
    }
}
