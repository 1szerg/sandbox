import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Ipv6WildcardToSQL
{
    public static void main(String... args)
    {
        //System.out.println(showFor("1:2:345:6789::Ab:cd"));
        //System.out.println(showFor("1:2:345:6789::Ab:%"));//1:2:345:6789:0:0:Ab:%
        System.out.println(showFor("::1%"));
    }

    private static String showFor(String ipv6)
    {
        StringBuilder sb = new StringBuilder();
        sb.append(ipv6).append(" => ");
        String[] variants = calculateConditions(ipv6);
        sb.append(Arrays.stream(variants).collect(Collectors.joining(", ")));
        return sb.toString();
    }


    public static String[] calculateConditions(String ipv6)
    {
        if (ipv6 == null || ipv6.isEmpty()) {
            return null;
        }
        String[] ipv6Variants = findVariantsIPv6(ipv6);
        return ipv6Variants;
    }

    private static String[] findVariantsIPv6(String ipv6)
    {
        List<String> variants = new LinkedList<>();
        String[] parts = ipv6.split(":");
        if(parts[0].isEmpty())parts[0]="0";
        if (!ipv6.contains("%")) {
            return Arrays.asList(expandIPv6(ipv6)).toArray(new String[1]);
        }
        parts = fillLeadingZeros(parts);
        variants.addAll(findVariantsExpandingZeros(parts));
        return variants.toArray(new String[variants.size()]);
    }

    private static List<String> findVariantsExpandingZeros(String[] parts)
    {
        List<String> cases = new LinkedList<>();
        for (int i = 0; i < parts.length; i++) {
            if (parts[i].isEmpty()) {
                int v = 8 - parts.length;
                if (v == 0)
                    throw new IllegalArgumentException("found :: in full address - :: should replace 2 and more of 0000");
                while (v > 0) {
                    cases.add(makeCaseWithZeros(parts, i, v+1));
                    v--;
                }
            }
        }
        return cases;
    }

    private static String makeCaseWithZeros(String[] parts, int replaceIndex, int replaceCount)
    {
        List<String> address = new LinkedList<>();
        address.addAll(Arrays.asList(parts));
        address.remove(replaceIndex);
        for (int i = 0; i < replaceCount; i++) {
            address.add(replaceIndex, "0000");
        }
        String[] res = address.toArray(new String[address.size()]);
        return Arrays.stream(res).collect(Collectors.joining(":"));
    }

    private static String[] fillLeadingZeros(String[] parts)
    {
        for (int i = 0; i < parts.length; i++) {
            if (!parts[i].contains("%") && !parts[i].isEmpty()) {
                while (parts[i].length() < 4) {
                    parts[i] = "0" + parts[i];
                }
            }
        }
        return parts;
    }

    private static String expandIPv6(String ipv6)
    {
        ipv6 = addMissingOctaves(ipv6);
        return expandOctaves(ipv6);
    }

    private static String expandOctaves(String ipv6)
    {
        String[] parts = ipv6.split(":");
        for (int i = 0; i < 8; i++) {
            while (parts[i].length() < 4) {
                parts[i] = "0" + parts[i];
            }
        }
        return Arrays.stream(parts).collect(Collectors.joining(":"));
    }

    private static String addMissingOctaves(String ipv6)
    {
        List<String> parts = new LinkedList<>();
        parts.addAll(Arrays.asList(ipv6.split(":")));
        if (parts.size() < 8) {
            for (int i = 0; i < parts.size(); i++) {
                String s = parts.get(i);
                if (s.isEmpty()) {
                    parts.remove(i);
                    for (int j = 0; j < 9 - parts.size(); j++) {
                        parts.add(i, "0");
                    }
                    break;
                }
            }
        }
        return Arrays.stream(parts.toArray(new String[8])).collect(Collectors.joining(":"));
    }
}
