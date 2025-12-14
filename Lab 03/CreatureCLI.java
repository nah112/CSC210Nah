class CreatureCLI {
public static void main(String[] args) {
try {
if (args.length < 1) throw new Exception();
CreatureRegistry r = new CreatureRegistry("creature-data.csv");
switch (args[0]) {
case "create": {
String[] p = args[1].split(" ");
r.add(parse(p));
r.save();
break;
}
case "read": {
System.out.println(r.get(Integer.parseInt(args[1])));
break;
}
case "delete": {
r.delete(Integer.parseInt(args[1]));
r.save();
break;
}
case "update": {
r.update(Integer.parseInt(args[1]), parse(args[2].split(" ")));
r.save();
break;
}
default: throw new Exception();
}
} catch (Exception e) {
System.err.println("Usage: create|read|update|delete");
System.exit(1);
}
}


private static Creature parse(String[] p) {
String n = p[0].split(":")[1];
int w = Integer.parseInt(p[1].split(":")[1]);
String c = p[2].split(":")[1];
return new Creature(n, w, c);
}
}